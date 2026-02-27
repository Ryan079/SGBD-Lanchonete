/**
 * Modelos relacionados a Cliente
 */

export interface ClienteRequest {
  cpf: string;
  nome: string;
  email?: string;
  telefone?: string;
  endereco?: string;
}

export interface ClienteResponse {
  cpf: string;
  nome: string;
  email?: string;
  telefone?: string;
  endereco?: string;
}
