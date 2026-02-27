import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import { CompraRequest, CompraResponse } from '../models';

@Injectable({
  providedIn: 'root'
})
export class CompraService {
  private http = inject(HttpClient);
  private baseUrl = `${API_BASE_URL}/compras`;

  registrar(compra: CompraRequest): Observable<CompraResponse> {
    return this.http.post<CompraResponse>(this.baseUrl, compra);
  }

  listarTodos(): Observable<CompraResponse[]> {
    return this.http.get<CompraResponse[]>(this.baseUrl);
  }
}
