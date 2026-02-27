import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import { PedidoRequest, PedidoResponse, PageResponse } from '../models';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {
  private http = inject(HttpClient);
  private baseUrl = `${API_BASE_URL}/pedidos`;

  criar(pedido: PedidoRequest): Observable<PedidoResponse> {
    return this.http.post<PedidoResponse>(this.baseUrl, pedido);
  }

  listarTodos(situacao?: string, page: number = 0, size: number = 10): Observable<PageResponse<PedidoResponse>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    
    if (situacao) {
      params = params.set('situacao', situacao);
    }

    return this.http.get<PageResponse<PedidoResponse>>(this.baseUrl, { params });
  }
}
