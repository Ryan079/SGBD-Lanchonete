import { Component, inject, signal, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SlicePipe } from '@angular/common';
import { PedidoService } from '../../services/pedido.service';
import { PedidoResponse } from '../../models';
import { ToastService } from '../../shared/toast/toast.service';
import { PedidoFormComponent } from './pedido-form.component';

@Component({
  selector: 'app-pedido-list',
  standalone: true,
  imports: [FormsModule, SlicePipe, PedidoFormComponent],
  templateUrl: './pedido-list.component.html'
})
export class PedidoListComponent implements OnInit {
  private service = inject(PedidoService);
  private toast = inject(ToastService);

  pedidos = signal<PedidoResponse[]>([]);
  loading = signal(false);
  situacao = '';
  page = 0;
  totalPages = 0;

  showForm = signal(false);

  situacoes = ['', 'Pendente', 'Em Preparo', 'Entregue', 'Cancelado'];

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

  badgeSituacao(s: string) {
    const m: Record<string, string> = { 'Pendente': 'badge--yellow', 'Em Preparo': 'badge--blue', 'Entregue': 'badge--green', 'Cancelado': 'badge--red' };
    return m[s] || 'badge--gray';
  }
  formatarPreco(p: number) { return p.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }); }
  anterior() { if (this.page > 0) { this.page--; this.carregar(); } }
  proximo()  { if (this.page < this.totalPages - 1) { this.page++; this.carregar(); } }
}
