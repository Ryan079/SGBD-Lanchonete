/**
 * Modelos relacionados a Views (Relatórios)
 */

export interface PedidoEmAbertoResponse {
  idPedido: number;
  dataHora: string;
  situacao: string;
  nomeCliente: string;
  telefoneCliente: string;
  valorTotal: number;
  enderecoEntrega?: string;
}

export interface PedidoCompletoResponse {
  idPedido: number;
  dataHora: string;
  situacao: string;
  nomeCliente: string;
  telefoneCliente: string;
  nomeProduto: string;
  quantidade: number;
  subtotalItem: number;
  metodoPagamento?: string;
  valorPago?: number;
}

export interface ProdutosMaisVendidosResponse {
  idCardapio: number;
  nomeProduto: string;
  categoria: string;
  precoUnitario: number;
  totalUnidadesVendidas: number;
  receitaTotal: number;
}

export interface EstoqueCriticoResponse {
  idProduto: number;
  nome: string;
  unidadeMedida: string;
  qtdEstoqueAtual: number;
  qtdEstoqueMinimo: number;
  qtdARepor: number;
  dataUltimaAtualizacao: string;
}
