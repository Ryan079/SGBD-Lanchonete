import { Component, inject, signal, OnInit } from '@angular/core';
import { FuncionarioService } from '../../services/funcionario.service';
import { FuncionarioResponse } from '../../models';
import { ToastService } from '../../shared/toast/toast.service';
import { ConfirmDialogService } from '../../shared/confirm-dialog/confirm-dialog.service';
import { FuncionarioFormComponent } from './funcionario-form.component';

@Component({
  selector: 'app-funcionario-list',
  standalone: true,
  imports: [FuncionarioFormComponent],
  templateUrl: './funcionario-list.component.html'
})
export class FuncionarioListComponent implements OnInit {
  private service = inject(FuncionarioService);
  private toast = inject(ToastService);
  private confirm = inject(ConfirmDialogService);

  funcionarios = signal<FuncionarioResponse[]>([]);
  loading = signal(false);
  showForm = signal(false);
  editingCpf = signal<string | null>(null);

  ngOnInit() { this.carregar(); }

  carregar() {
    this.loading.set(true);
    this.service.listarTodos().subscribe({
      next: r => { this.funcionarios.set(r); this.loading.set(false); },
      error: () => { this.toast.error('Erro ao carregar funcionários'); this.loading.set(false); }
    });
  }

  novo() { this.editingCpf.set(null); this.showForm.set(true); }
  editar(cpf: string) { this.editingCpf.set(cpf); this.showForm.set(true); }
  fecharForm() { this.showForm.set(false); this.carregar(); }

  async excluir(cpf: string, nome: string) {
    const ok = await this.confirm.confirm(`Deseja excluir o funcionário "${nome}"?`);
    if (!ok) return;
    this.service.deletar(cpf).subscribe({
      next: () => { this.toast.success('Funcionário excluído!'); this.carregar(); },
      error: () => this.toast.error('Erro ao excluir funcionário')
    });
  }

  formatarCpf(cpf: string) {
    return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
  }
  formatarSalario(s: number) { return s.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }); }
}
