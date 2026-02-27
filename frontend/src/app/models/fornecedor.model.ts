/**
 * Modelos relacionados a Fornecedor
 */

export interface FornecedorRequest {
  cnpj: string;
  nomeEmpresa: string;
  dataCadastro: string; // ISO date string
  telefone: string;
}

export interface FornecedorResponse {
  cnpj: string;
  nomeEmpresa: string;
  dataCadastro: string;
  telefone: string;
}
