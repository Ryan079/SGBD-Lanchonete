import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RelatoriosService } from '../../services';
import { FaturamentoDiario } from '../../models';

@Component({
  selector: 'app-relatorio-faturamento',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './relatorio-faturamento.component.html',
  styleUrls: ['./relatorio-faturamento.component.css']
})
export class RelatorioFaturamentoComponent implements OnInit {
  private relatoriosService = inject(RelatoriosService);
  
  dados = signal<FaturamentoDiario[]>([]);
  loading = signal(false);
  dataInicio = signal('');
  dataFim = signal('');

  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados(): void {
    this.loading.set(true);
    
    const inicio = this.dataInicio() || undefined;
    const fim = this.dataFim() || undefined;
    
    this.relatoriosService.getFaturamentoDiario(inicio, fim).subscribe({
      next: (data) => {
        this.dados.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        console.error(err);
        this.loading.set(false);
      }
    });
  }

  exportar(formato: 'pdf' | 'csv'): void {
    const inicio = this.dataInicio() || undefined;
    const fim = this.dataFim() || undefined;
    
    this.relatoriosService.exportarFaturamentoDiario(formato, inicio, fim).subscribe({
      next: (blob) => {
        this.relatoriosService.downloadArquivo(blob, `faturamento_diario.${formato}`);
      },
      error: (err) => console.error(err)
    });
  }

  formatarMoeda(valor: number): string {
    return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
  }

  formatarData(data: string): string {
    return new Date(data).toLocaleDateString('pt-BR');
  }
}
