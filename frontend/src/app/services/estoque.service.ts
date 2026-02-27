import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import { EstoqueRequest, EstoqueResponse, PageResponse } from '../models';

@Injectable({
  providedIn: 'root'
})
export class EstoqueService {
  private http = inject(HttpClient);
  private baseUrl = `${API_BASE_URL}/estoque`;

  criar(estoque: EstoqueRequest): Observable<EstoqueResponse> {
    return this.http.post<EstoqueResponse>(this.baseUrl, estoque);
  }

  listarTodos(nome?: string, page: number = 0, size: number = 10): Observable<PageResponse<EstoqueResponse>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    
    if (nome) {
      params = params.set('nome', nome);
    }

    return this.http.get<PageResponse<EstoqueResponse>>(this.baseUrl, { params });
  }

  buscarPorId(id: number): Observable<EstoqueResponse> {
    return this.http.get<EstoqueResponse>(`${this.baseUrl}/${id}`);
  }

  atualizar(id: number, estoque: EstoqueRequest): Observable<EstoqueResponse> {
    return this.http.put<EstoqueResponse>(`${this.baseUrl}/${id}`, estoque);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
