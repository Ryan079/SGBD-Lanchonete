/**
 * Modelos relacionados a Estoque
 */

export interface EstoqueRequest {
  nome: string;
  unidadeMedida: string;
  qtdEstoqueAtual?: number;
  qtdEstoqueMinimo?: number;
}

export interface EstoqueResponse {
  idProduto: number;
  nome: string;
  unidadeMedida: string;
  qtdEstoqueAtual: number;
  qtdEstoqueMinimo: number;
  dataUltimaAtualizacao: string;
}
