import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import {
  PedidoEmAbertoResponse,
  PedidoCompletoResponse,
  ProdutosMaisVendidosResponse,
  EstoqueCriticoResponse
} from '../models';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {
  private http = inject(HttpClient);
  private baseUrl = `${API_BASE_URL}/reports`;

  // Pedidos em Aberto
  listarPedidosEmAberto(situacao: string): Observable<PedidoEmAbertoResponse[]> {
    const params = new HttpParams().set('situacao', situacao);
    return this.http.get<PedidoEmAbertoResponse[]>(`${this.baseUrl}/pedidos-em-aberto/situacao`, { params });
  }

  // Pedidos Completos
  buscarPedidoCompletoPorId(idPedido: number): Observable<PedidoCompletoResponse[]> {
    return this.http.get<PedidoCompletoResponse[]>(`${this.baseUrl}/pedidos/${idPedido}`);
  }

  listarPedidosCompletosPorSituacao(situacao: string): Observable<PedidoCompletoResponse[]> {
    const params = new HttpParams().set('situacao', situacao);
    return this.http.get<PedidoCompletoResponse[]>(`${this.baseUrl}/pedidos/situacao`, { params });
  }

  listarPedidosCompletosPorCliente(cpfCliente: string): Observable<PedidoCompletoResponse[]> {
    const params = new HttpParams().set('clienteCpf', cpfCliente);
    return this.http.get<PedidoCompletoResponse[]>(`${this.baseUrl}/pedidos/cliente`, { params });
  }

  // Produtos Mais Vendidos
  listarProdutosMaisVendidosPorCategoria(categoria: string): Observable<ProdutosMaisVendidosResponse[]> {
    const params = new HttpParams().set('categoria', categoria);
    return this.http.get<ProdutosMaisVendidosResponse[]>(`${this.baseUrl}/produtos-mais-vendidos/categoria`, { params });
  }

  // Estoque Crítico
  listarEstoqueCritico(): Observable<EstoqueCriticoResponse[]> {
    return this.http.get<EstoqueCriticoResponse[]>(`${this.baseUrl}/estoque/critico`);
  }
}
