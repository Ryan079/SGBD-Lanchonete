import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RelatoriosService } from '../../services';
import { HorarioPico } from '../../models';

@Component({
  selector: 'app-relatorio-horarios',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="relatorio-container">
      <h2>Horários de Pico</h2>
      
      <div class="acoes">
        <button class="btn-export" (click)="exportar('pdf')">📄 PDF</button>
        <button class="btn-export" (click)="exportar('csv')">📊 CSV</button>
      </div>

      <table *ngIf="dados().length > 0" class="tabela-relatorio">
        <thead>
          <tr>
            <th>Hora</th>
            <th>Quantidade de Pedidos</th>
            <th>Ticket Médio</th>
            <th>Faturamento Total</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let item of dados()">
            <td>{{ item.hora }}:00</td>
            <td>{{ item.quantidadePedidos }}</td>
            <td>{{ formatarMoeda(item.ticketMedio) }}</td>
            <td>{{ formatarMoeda(item.faturamentoTotal) }}</td>
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
export class RelatorioHorariosComponent implements OnInit {
  private relatoriosService = inject(RelatoriosService);
  dados = signal<HorarioPico[]>([]);

  ngOnInit(): void {
    this.relatoriosService.getHorariosPico().subscribe({
      next: (data) => this.dados.set(data),
      error: (err) => console.error(err)
    });
  }

  exportar(formato: 'pdf' | 'csv'): void {
    this.relatoriosService.exportarHorariosPico(formato).subscribe({
      next: (blob) => this.relatoriosService.downloadArquivo(blob, `horarios_pico.${formato}`),
      error: (err) => console.error(err)
    });
  }

  formatarMoeda(valor: number): string {
    return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
  }
}
