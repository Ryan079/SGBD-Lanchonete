import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReportsService } from '../../services/reports.service';
import { 
  PedidoEmAbertoResponse, 
  EstoqueCriticoResponse, 
  ProdutosMaisVendidosResponse 
} from '../../models';

@Component({
  selector: 'app-reports-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './reports-list.component.html',
  styleUrls: ['./reports-list.component.css']
})
export class ReportsListComponent implements OnInit {
  private reportsService = inject(ReportsService);

  pedidosAberto: PedidoEmAbertoResponse[] = [];
  estoqueCritico: EstoqueCriticoResponse[] = [];
  produtosPopulares: ProdutosMaisVendidosResponse[] = [];

  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados(): void {
    // pedido em aberto
    this.reportsService.listarPedidosEmAberto('Pendente').subscribe(res => this.pedidosAberto = res);

    // estoque critico
    this.reportsService.listarEstoqueCritico().subscribe(res => this.estoqueCritico = res);

    // mais vendidos
    this.reportsService.listarProdutosMaisVendidosPorCategoria('Lanche').subscribe(res => this.produtosPopulares = res);
  }
}