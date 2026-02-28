import { Component, inject, signal, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SlicePipe } from '@angular/common';
import { PedidoService } from '../../services/pedido.service';
import { PedidoResponse } from '../../models';
import { ToastService } from '../../shared/toast/toast.service';
import { ConfirmDialogService } from '../../shared/confirm-dialog/confirm-dialog.service';
import { PedidoFormComponent } from './pedido-form.component';
import { PedidoEditComponent } from './pedido-edit.component';

@Component({
  selector: 'app-pedido-list',
  standalone: true,
  imports: [FormsModule, SlicePipe, PedidoFormComponent, PedidoEditComponent],
  templateUrl: './pedido-list.component.html'
})
export class PedidoListComponent implements OnInit {
  private service = inject(PedidoService);
  private toast = inject(ToastService);
  private confirm = inject(ConfirmDialogService);

  pedidos = signal<PedidoResponse[]>([]);
  loading = signal(false);
  situacao = '';
  page = 0;
  totalPages = 0;

  showForm = signal(false);
  showEdit = signal(false);
  pedidoEmEdicao = signal<PedidoResponse | null>(null);

  ngOnInit() { this.carregar(); }

  carregar() {
    this.loading.set(true);
    this.service.listarTodos(this.situacao || undefined, this.page, 10).subscribe({
      next: res => { this.pedidos.set(res.content); this.totalPages = res.totalPages; this.loading.set(false); },
      error: () => { this.toast.error('Erro ao carregar pedidos'); this.loading.set(false); }
    });
  }

  filtrar() { this.page = 0; this.carregar(); }

  novoPedido() { this.showForm.set(true); }
  fecharForm() { this.showForm.set(false); this.carregar(); }

  editar(pedido: PedidoResponse) { this.pedidoEmEdicao.set(pedido); this.showEdit.set(true); }
  fecharEdit() { this.showEdit.set(false); this.pedidoEmEdicao.set(null); this.carregar(); }

  async excluir(pedido: PedidoResponse) {
    const ok = await this.confirm.confirm(
      `Deseja excluir o pedido #${pedido.idPedido} do cliente ${pedido.cpfCliente}? Esta ação também removerá os itens e pagamentos vinculados.`
    );
    if (!ok) return;
    this.service.deletar(pedido.idPedido).subscribe({
      next: () => { this.toast.success(`Pedido #${pedido.idPedido} excluído!`); this.carregar(); },
      error: () => this.toast.error('Erro ao excluir pedido')
    });
  }

  formatarPreco(v: number) { return v.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }); }

  badgeSituacao(s: string) {
    const m: Record<string, string> = {
      'Pendente': 'badge--yellow',
      'Em Preparo': 'badge--blue',
      'Entregue': 'badge--green',
      'Cancelado': 'badge--red'
    };
    return m[s] || 'badge--gray';
  }

  anterior() { if (this.page > 0) { this.page--; this.carregar(); } }
  proximo()  { if (this.page < this.totalPages - 1) { this.page++; this.carregar(); } }
}

