import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import { CardapioRequest, CardapioResponse, PageResponse } from '../models';

@Injectable({
  providedIn: 'root'
})
export class CardapioService {
  private http = inject(HttpClient);
  private baseUrl = `${API_BASE_URL}/cardapio`;

  criar(cardapio: CardapioRequest): Observable<CardapioResponse> {
    return this.http.post<CardapioResponse>(this.baseUrl, cardapio);
  }

  listarTodos(categoria?: string, page: number = 0, size: number = 10): Observable<PageResponse<CardapioResponse>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    
    if (categoria) {
      params = params.set('categoria', categoria);
    }

    return this.http.get<PageResponse<CardapioResponse>>(this.baseUrl, { params });
  }

  buscarPorId(id: number): Observable<CardapioResponse> {
    return this.http.get<CardapioResponse>(`${this.baseUrl}/${id}`);
  }

  atualizar(id: number, cardapio: CardapioRequest): Observable<CardapioResponse> {
    return this.http.put<CardapioResponse>(`${this.baseUrl}/${id}`, cardapio);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
