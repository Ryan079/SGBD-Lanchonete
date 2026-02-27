import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import { PagamentoRequest, PagamentoResponse } from '../models';

@Injectable({
  providedIn: 'root'
})
export class PagamentoService {
  private http = inject(HttpClient);
  private baseUrl = `${API_BASE_URL}/pagamentos`;

  registrar(pagamento: PagamentoRequest): Observable<PagamentoResponse> {
    return this.http.post<PagamentoResponse>(this.baseUrl, pagamento);
  }

  listarTodos(): Observable<PagamentoResponse[]> {
    return this.http.get<PagamentoResponse[]>(this.baseUrl);
  }
}
