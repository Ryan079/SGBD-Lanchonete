import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import { ClienteRequest, ClienteResponse, PageResponse } from '../models';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private http = inject(HttpClient);
  private baseUrl = `${API_BASE_URL}/clientes`;

  criar(cliente: ClienteRequest): Observable<ClienteResponse> {
    return this.http.post<ClienteResponse>(this.baseUrl, cliente);
  }

  listarTodos(nome?: string, page: number = 0, size: number = 10): Observable<PageResponse<ClienteResponse>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    
    if (nome) {
      params = params.set('nome', nome);
    }

    return this.http.get<PageResponse<ClienteResponse>>(this.baseUrl, { params });
  }

  buscarPorCpf(cpf: string): Observable<ClienteResponse> {
    return this.http.get<ClienteResponse>(`${this.baseUrl}/${cpf}`);
  }

  atualizar(cpf: string, cliente: ClienteRequest): Observable<ClienteResponse> {
    return this.http.put<ClienteResponse>(`${this.baseUrl}/${cpf}`, cliente);
  }

  deletar(cpf: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${cpf}`);
  }
}
