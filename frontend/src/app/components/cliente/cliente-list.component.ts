import { Component, inject, signal, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SlicePipe } from '@angular/common';
import { ClienteService } from '../../services/cliente.service';
import { ClienteResponse } from '../../models';
import { ToastService } from '../../shared/toast/toast.service';
import { ConfirmDialogService } from '../../shared/confirm-dialog/confirm-dialog.service';
import { ClienteFormComponent } from './cliente-form.component';

@Component({
  selector: 'app-cliente-list',
  standalone: true,
  imports: [FormsModule, SlicePipe, ClienteFormComponent],
  templateUrl: './cliente-list.component.html'
})
export class ClienteListComponent implements OnInit {
  private service = inject(ClienteService);
  private toast = inject(ToastService);
  private confirm = inject(ConfirmDialogService);

  clientes = signal<ClienteResponse[]>([]);
  loading = signal(false);
  busca = '';
  page = 0;
  totalPages = 0;

  showForm = signal(false);
  editingCpf = signal<string | null>(null);

  ngOnInit() { this.carregar(); }

  carregar() {
    this.loading.set(true);
    this.service.listarTodos(this.busca || undefined, this.page, 10).subscribe({
      next: res => {
        this.clientes.set(res.content);
        this.totalPages = res.totalPages;
        this.loading.set(false);
      },
      error: () => { this.toast.error('Erro ao carregar clientes'); this.loading.set(false); }
    });
  }

  pesquisar() { this.page = 0; this.carregar(); }

  novoCliente() { this.editingCpf.set(null); this.showForm.set(true); }
  editar(cpf: string) { this.editingCpf.set(cpf); this.showForm.set(true); }
  fecharForm() { this.showForm.set(false); this.carregar(); }

  async excluir(cpf: string, nome: string) {
    const ok = await this.confirm.confirm(`Deseja excluir o cliente "${nome}"? Esta ação não pode ser desfeita.`);
    if (!ok) return;
    this.service.deletar(cpf).subscribe({
      next: () => { this.toast.success('Cliente excluído com sucesso'); this.carregar(); },
      error: () => this.toast.error('Erro ao excluir cliente')
    });
  }

  anterior() { if (this.page > 0) { this.page--; this.carregar(); } }
  proximo()  { if (this.page < this.totalPages - 1) { this.page++; this.carregar(); } }
}
