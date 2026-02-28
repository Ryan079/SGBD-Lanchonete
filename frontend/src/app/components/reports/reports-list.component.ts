import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReportsService } from '../../services/reports.service';
import { ToastService } from '../../shared/toast/toast.service';
import { 
  PedidoEmAbertoResponse, 
  EstoqueCriticoResponse, 
  ProdutosMaisVendidosResponse,
  PedidoCompletoResponse 
} from '../../models';

@Component({
  selector: 'app-reports-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './reports-list.component.html'
})
export class ReportsListComponent implements OnInit {
  private reportsService = inject(ReportsService);
  private toast = inject(ToastService);

  // visoes
  pedidosAberto: PedidoEmAbertoResponse[] = [];
  estoqueCritico: EstoqueCriticoResponse[] = [];
  produtosPopulares: ProdutosMaisVendidosResponse[] = [];
  detalhePedido: PedidoCompletoResponse[] = [];

  // filtros
  categoriaSelecionada: string = 'Lanche';
  idBuscaPedido: number | null = null;
  loading: boolean = false;

  ngOnInit(): void {
    this.carregarIndicadores();
  }

  carregarIndicadores(): void {
    this.loading = true;
    // pedidos em aberto
    this.reportsService.listarPedidosEmAberto('Pendente').subscribe({
      next: (res) => this.pedidosAberto = res,
      error: () => this.toast.error('Erro ao carregar fila de pedidos')
    });

    // alertas de estoque crítico
    this.reportsService.listarEstoqueCritico().subscribe({
      next: (res) => this.estoqueCritico = res,
      error: () => this.toast.error('Erro ao carregar alertas de stock')
    });

    this.atualizarRanking();
  }

  // produtos mais vendidos por categoria
  atualizarRanking(): void {
    this.reportsService.listarProdutosMaisVendidosPorCategoria(this.categoriaSelecionada).subscribe({
      next: (res) => {
        this.produtosPopulares = res;
        this.loading = false;
      }
    });
  }

  // buscar detalhes de um pedido
  buscarPedido(): void {
    if (!this.idBuscaPedido) return;
    this.reportsService.buscarPedidoCompletoPorId(this.idBuscaPedido).subscribe({
      next: (res) => {
        this.detalhePedido = res;
        if (res.length === 0) this.toast.warning('Pedido não encontrado.');
      },
      error: () => this.toast.error('Erro ao buscar detalhes do pedido')
    });
  }
}