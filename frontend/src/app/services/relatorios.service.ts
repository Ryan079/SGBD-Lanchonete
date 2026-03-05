import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import {
  FaturamentoDiario,
  ClienteFrequente,
  HorarioPico,
  AnalisePagamento,
  ProdutoBaixaRotatividade,
  DashboardExecutivo,
  RankingCategoria,
  FormatoExportacao
} from '../models';

@Injectable({
  providedIn: 'root'
})
export class RelatoriosService {
  private http = inject(HttpClient);
  private baseUrl = `${API_BASE_URL}/reports`;

  // ========== DASHBOARD EXECUTIVO ==========
  
  getDashboardExecutivo(): Observable<DashboardExecutivo> {
    return this.http.get<DashboardExecutivo>(`${this.baseUrl}/dashboard-executivo`);
  }

  // ========== FATURAMENTO DIÁRIO ==========
  
  getFaturamentoDiario(dataInicio?: string, dataFim?: string): Observable<FaturamentoDiario[]> {
    let params = new HttpParams();
    
    if (dataInicio && dataFim) {
      return this.http.get<FaturamentoDiario[]>(`${this.baseUrl}/faturamento-diario/periodo`, {
        params: params.set('dataInicio', dataInicio).set('dataFim', dataFim)
      });
    }
    
    return this.http.get<FaturamentoDiario[]>(`${this.baseUrl}/faturamento-diario`);
  }

  exportarFaturamentoDiario(formato: FormatoExportacao, dataInicio?: string, dataFim?: string): Observable<Blob> {
    let params = new HttpParams();
    
    if (dataInicio && dataFim) {
      params = params.set('dataInicio', dataInicio).set('dataFim', dataFim);
    }
    
    return this.http.get(`${this.baseUrl}/export/faturamento-diario/${formato}`, {
      params,
      responseType: 'blob'
    });
  }

  // ========== CLIENTES FREQUENTES ==========
  
  getClientesFrequentes(): Observable<ClienteFrequente[]> {
    return this.http.get<ClienteFrequente[]>(`${this.baseUrl}/clientes-frequentes`);
  }

  exportarClientesFrequentes(formato: FormatoExportacao): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/export/clientes-frequentes/${formato}`, {
      responseType: 'blob'
    });
  }

  // ========== HORÁRIOS DE PICO ==========
  
  getHorariosPico(): Observable<HorarioPico[]> {
    return this.http.get<HorarioPico[]>(`${this.baseUrl}/horarios-pico`);
  }

  exportarHorariosPico(formato: FormatoExportacao): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/export/horarios-pico/${formato}`, {
      responseType: 'blob'
    });
  }

  // ========== ANÁLISE DE PAGAMENTOS ==========
  
  getAnalisePagamentos(): Observable<AnalisePagamento[]> {
    return this.http.get<AnalisePagamento[]>(`${this.baseUrl}/analise-pagamentos`);
  }

  exportarAnalisePagamentos(formato: FormatoExportacao): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/export/analise-pagamentos/${formato}`, {
      responseType: 'blob'
    });
  }

  // ========== PRODUTOS COM BAIXA ROTATIVIDADE ==========
  
  getProdutosBaixaRotatividade(): Observable<ProdutoBaixaRotatividade[]> {
    return this.http.get<ProdutoBaixaRotatividade[]>(`${this.baseUrl}/produtos-baixa-rotatividade`);
  }

  exportarProdutosBaixaRotatividade(formato: FormatoExportacao): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/export/produtos-baixa-rotatividade/${formato}`, {
      responseType: 'blob'
    });
  }

  // ========== RANKING DE CATEGORIAS ==========
  
  getRankingCategorias(): Observable<RankingCategoria[]> {
    return this.http.get<RankingCategoria[]>(`${this.baseUrl}/ranking-categorias`);
  }

  exportarRankingCategorias(formato: FormatoExportacao): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/export/ranking-categorias/${formato}`, {
      responseType: 'blob'
    });
  }

  // ========== MÉTODO AUXILIAR PARA DOWNLOAD DE ARQUIVOS ==========
  
  downloadArquivo(blob: Blob, nomeArquivo: string): void {
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = nomeArquivo;
    link.click();
    window.URL.revokeObjectURL(url);
  }
}
