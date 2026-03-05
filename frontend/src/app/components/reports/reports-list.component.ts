import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReportsService } from '../../services/reports.service';
import { ToastService } from '../../shared/toast/toast.service';
import { 
  ProdutosMaisVendidosResponse,
  PedidoCompletoResponse 
} from '../../models';

/**
 * Painel Operacional - Funcionalidades ÚNICAS:
 * 1. Rastreabilidade de Pedidos (busca por ID)
 * 2. Ranking dinâmico por categoria
 */
@Component({
  selector: 'app-reports-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './reports-list.component.html',
  styleUrls: ['./reports-list.component.css']
})
export class ReportsListComponent {
  private reportsService = inject(ReportsService);
  private toast = inject(ToastService);

  // FUNCIONALIDADE 1: Rastreabilidade de Pedido
  idBuscaPedido = signal<number | null>(null);
  detalhePedido = signal<PedidoCompletoResponse[]>([]);
  buscandoPedido = signal(false);

  // FUNCIONALIDADE 2: Ranking por Categoria
  categoriaSelecionada = signal<string>('Lanche');
  produtosPopulares = signal<ProdutosMaisVendidosResponse[]>([]);
  carregandoRanking = signal(false);

  constructor() {
    this.atualizarRanking();
  }

  buscarPedido(): void {
    const id = this.idBuscaPedido();
    if (!id) {
      this.toast.warning('Digite um ID de pedido válido');
      return;
    }

    this.buscandoPedido.set(true);
    this.reportsService.buscarPedidoCompletoPorId(id).subscribe({
      next: (res) => {
        if (res.length === 0) {
          this.toast.warning(`Pedido #${id} não encontrado`);
          this.detalhePedido.set([]);
        } else {
          this.detalhePedido.set(res);
          this.toast.success(`Pedido #${id} carregado`);
        }
        this.buscandoPedido.set(false);
      },
      error: (err) => {
        this.toast.error(`Erro ao buscar: ${err.message}`);
        this.buscandoPedido.set(false);
      }
    });
  }

  atualizarRanking(): void {
    const categoria = this.categoriaSelecionada();
    this.carregandoRanking.set(true);
    
    this.reportsService.listarProdutosMaisVendidosPorCategoria(categoria).subscribe({
      next: (res) => {
        this.produtosPopulares.set(res);
        this.carregandoRanking.set(false);
      },
      error: (err) => {
        this.toast.error(`Erro ao carregar ranking: ${err.message}`);
        this.carregandoRanking.set(false);
      }
    });
  }

  calcularPercentual(unidades: number): number {
    return Math.min((unidades / 100) * 100, 100);
  }
}