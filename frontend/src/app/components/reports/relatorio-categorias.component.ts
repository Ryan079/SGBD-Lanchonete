import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RelatoriosService } from '../../services';
import { RankingCategoria } from '../../models';

@Component({
  selector: 'app-relatorio-categorias',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="relatorio-container">
      <h2>Ranking de Categorias</h2>
      
      <div class="acoes">
        <button class="btn-export" (click)="exportar('pdf')">📄 PDF</button>
        <button class="btn-export" (click)="exportar('csv')">📊 CSV</button>
      </div>

      <table *ngIf="dados().length > 0" class="tabela-relatorio">
        <thead>
          <tr>
            <th>Categoria</th>
            <th>Total de Produtos</th>
            <th>Unidades Vendidas</th>
            <th>Receita Total</th>
            <th>Preço Médio</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let item of dados()">
            <td>{{ item.categoria }}</td>
            <td>{{ item.totalProdutos }}</td>
            <td>{{ item.totalUnidadesVendidas }}</td>
            <td>{{ formatarMoeda(item.receitaTotal) }}</td>
            <td>{{ formatarMoeda(item.precoMedio) }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  `,
  styles: [`
    .relatorio-container {
      background: white;
      padding: 25px;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    .acoes {
      display: flex;
      gap: 10px;
      margin-bottom: 20px;
    }
    .btn-export {
      padding: 10px 20px;
      background-color: #2196F3;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
    .tabela-relatorio {
      width: 100%;
      border-collapse: collapse;
    }
    .tabela-relatorio th {
      background-color: #2196F3;
      color: white;
      padding: 12px;
      text-align: left;
    }
    .tabela-relatorio td {
      padding: 12px;
      border-bottom: 1px solid #ddd;
    }
  `]
})
export class RelatorioCategoriasComponent implements OnInit {
  private relatoriosService = inject(RelatoriosService);
  dados = signal<RankingCategoria[]>([]);

  ngOnInit(): void {
    this.relatoriosService.getRankingCategorias().subscribe({
      next: (data) => this.dados.set(data),
      error: (err) => console.error(err)
    });
  }

  exportar(formato: 'pdf' | 'csv'): void {
    this.relatoriosService.exportarRankingCategorias(formato).subscribe({
      next: (blob) => this.relatoriosService.downloadArquivo(blob, `ranking_categorias.${formato}`),
      error: (err) => console.error(err)
    });
  }

  formatarMoeda(valor: number): string {
    return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
  }
}
