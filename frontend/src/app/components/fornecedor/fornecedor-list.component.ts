import { Component, inject, signal, OnInit } from '@angular/core';
import { FornecedorService } from '../../services/fornecedor.service';
import { FornecedorResponse } from '../../models';
import { ToastService } from '../../shared/toast/toast.service';
import { ConfirmDialogService } from '../../shared/confirm-dialog/confirm-dialog.service';
import { FornecedorFormComponent } from './fornecedor-form.component';

@Component({
  selector: 'app-fornecedor-list',
  standalone: true,
  imports: [FornecedorFormComponent],
  templateUrl: './fornecedor-list.component.html'
})
export class FornecedorListComponent implements OnInit {
  private service = inject(FornecedorService);
  private toast = inject(ToastService);
  private confirm = inject(ConfirmDialogService);

  fornecedores = signal<FornecedorResponse[]>([]);
  loading = signal(false);
  showForm = signal(false);
  editingCnpj = signal<string | null>(null);
  page = 0;
  pageSize = 10;

  get totalPages() { return Math.ceil(this.fornecedores().length / this.pageSize) || 1; }
  get paginados() { return this.fornecedores().slice(this.page * this.pageSize, (this.page + 1) * this.pageSize); }

  ngOnInit() { this.carregar(); }

  carregar() {
    this.loading.set(true);
    this.service.listarTodos().subscribe({
      next: r => { this.fornecedores.set(r); this.page = 0; this.loading.set(false); },
      error: () => { this.toast.error('Erro ao carregar fornecedores'); this.loading.set(false); }
    });
  }

  anterior() { if (this.page > 0) this.page--; }
  proximo()  { if (this.page < this.totalPages - 1) this.page++; }

  novo() { this.editingCnpj.set(null); this.showForm.set(true); }
  editar(cnpj: string) { this.editingCnpj.set(cnpj); this.showForm.set(true); }
  fecharForm() { this.showForm.set(false); this.carregar(); }

  async excluir(cnpj: string, nome: string) {
    const ok = await this.confirm.confirm(`Deseja excluir o fornecedor "${nome}"?`);
    if (!ok) return;
    this.service.deletar(cnpj).subscribe({
      next: () => { this.toast.success('Fornecedor excluído!'); this.carregar(); },
      error: () => this.toast.error('Erro ao excluir fornecedor')
    });
  }

  formatarCnpj(cnpj: string) {
    return cnpj.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/, '$1.$2.$3/$4-$5');
  }
}
