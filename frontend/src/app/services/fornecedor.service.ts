import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import { FornecedorRequest, FornecedorResponse } from '../models';

@Injectable({
  providedIn: 'root'
})
export class FornecedorService {
  private http = inject(HttpClient);
  private baseUrl = `${API_BASE_URL}/fornecedores`;

  criar(fornecedor: FornecedorRequest): Observable<FornecedorResponse> {
    return this.http.post<FornecedorResponse>(this.baseUrl, fornecedor);
  }

  listarTodos(): Observable<FornecedorResponse[]> {
    return this.http.get<FornecedorResponse[]>(this.baseUrl);
  }

  buscarPorCnpj(cnpj: string): Observable<FornecedorResponse> {
    return this.http.get<FornecedorResponse>(`${this.baseUrl}/${cnpj}`);
  }

  atualizar(cnpj: string, fornecedor: FornecedorRequest): Observable<FornecedorResponse> {
    return this.http.put<FornecedorResponse>(`${this.baseUrl}/${cnpj}`, fornecedor);
  }

  deletar(cnpj: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${cnpj}`);
  }
}
