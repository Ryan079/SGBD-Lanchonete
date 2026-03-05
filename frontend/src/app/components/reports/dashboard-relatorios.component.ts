import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RelatoriosService } from '../../services';
import { DashboardExecutivo } from '../../models';
import { RelatorioFaturamentoComponent } from './relatorio-faturamento.component';
import { RelatorioClientesComponent } from './relatorio-clientes.component';
import { RelatorioHorariosComponent } from './relatorio-horarios.component';
import { RelatorioPagamentosComponent } from './relatorio-pagamentos.component';
import { RelatorioProdutosComponent } from './relatorio-produtos.component';
import { RelatorioCategoriasComponent } from './relatorio-categorias.component';

@Component({
  selector: 'app-dashboard-relatorios',
  standalone: true,
  imports: [
    CommonModule, 
    FormsModule,
    RelatorioFaturamentoComponent,
    RelatorioClientesComponent,
    RelatorioHorariosComponent,
    RelatorioPagamentosComponent,
    RelatorioProdutosComponent,
    RelatorioCategoriasComponent
  ],
  templateUrl: './dashboard-relatorios.component.html',
  styleUrls: ['./dashboard-relatorios.component.css']
})
export class DashboardRelatoriosComponent implements OnInit {
  private relatoriosService = inject(RelatoriosService);
  
  dashboardData = signal<DashboardExecutivo | null>(null);
  loading = signal(false);
  error = signal<string | null>(null);
  
  relatorioSelecionado = signal<string>('dashboard');

  ngOnInit(): void {
    this.carregarDashboard();
  }

  carregarDashboard(): void {
    this.loading.set(true);
    this.error.set(null);
    
    this.relatoriosService.getDashboardExecutivo().subscribe({
      next: (data) => {
        this.dashboardData.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        this.error.set('Erro ao carregar dados do dashboard');
        this.loading.set(false);
        console.error(err);
      }
    });
  }

  selecionarRelatorio(tipo: string): void {
    this.relatorioSelecionado.set(tipo);
  }

  formatarMoeda(valor: number | undefined): string {
    if (valor === undefined || valor === null) return 'R$ 0,00';
    return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
  }
}
