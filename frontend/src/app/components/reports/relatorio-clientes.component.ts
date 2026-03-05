import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RelatoriosService } from '../../services';
import { ClienteFrequente } from '../../models';

@Component({
  selector: 'app-relatorio-clientes',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="relatorio-container">
      <h2>Clientes Frequentes</h2>
      
      <div class="acoes">
        <button class="btn-export" (click)="exportar('pdf')">📄 PDF</button>
        <button class="btn-export" (click)="exportar('csv')">📊 CSV</button>
      </div>

      <table *ngIf="dados().length > 0" class="tabela-relatorio">
        <thead>
          <tr>
            <th>Cliente</th>
            <th>Telefone</th>
            <th>Total Pedidos</th>
            <th>Valor Gasto</th>
            <th>Ticket Médio</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let item of dados()">
            <td>{{ item.cliente }}</td>
            <td>{{ item.telefone }}</td>
            <td>{{ item.totalPedidos }}</td>
            <td>{{ formatarMoeda(item.valorTotalGasto) }}</td>
            <td>{{ formatarMoeda(item.ticketMedio) }}</td>
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
export class RelatorioClientesComponent implements OnInit {
  private relatoriosService = inject(RelatoriosService);
  dados = signal<ClienteFrequente[]>([]);

  ngOnInit(): void {
    this.relatoriosService.getClientesFrequentes().subscribe({
      next: (data) => this.dados.set(data),
      error: (err) => console.error(err)
    });
  }

  exportar(formato: 'pdf' | 'csv'): void {
    this.relatoriosService.exportarClientesFrequentes(formato).subscribe({
      next: (blob) => this.relatoriosService.downloadArquivo(blob, `clientes_frequentes.${formato}`),
      error: (err) => console.error(err)
    });
  }

  formatarMoeda(valor: number): string {
    return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
  }
}
