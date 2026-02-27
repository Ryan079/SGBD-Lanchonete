import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import { FuncionarioRequest, FuncionarioResponse } from '../models';

@Injectable({
  providedIn: 'root'
})
export class FuncionarioService {
  private http = inject(HttpClient);
  private baseUrl = `${API_BASE_URL}/funcionarios`;

  criar(funcionario: FuncionarioRequest): Observable<FuncionarioResponse> {
    return this.http.post<FuncionarioResponse>(this.baseUrl, funcionario);
  }

  listarTodos(): Observable<FuncionarioResponse[]> {
    return this.http.get<FuncionarioResponse[]>(this.baseUrl);
  }

  buscarPorCpf(cpf: string): Observable<FuncionarioResponse> {
    return this.http.get<FuncionarioResponse>(`${this.baseUrl}/${cpf}`);
  }

  atualizar(cpf: string, funcionario: FuncionarioRequest): Observable<FuncionarioResponse> {
    return this.http.put<FuncionarioResponse>(`${this.baseUrl}/${cpf}`, funcionario);
  }

  deletar(cpf: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${cpf}`);
  }
}
