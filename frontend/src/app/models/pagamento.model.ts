/**
 * Modelos relacionados a Pagamento
 */

export interface PagamentoRequest {
  idPedido: number;
  valorPago: number;
  metodoPagamento: string;
}

export interface PagamentoResponse {
  idPagamento: number;
  idPedido: number;
  valorPago: number;
  metodoPagamento: string;
  datahoraPagamento: string;
}
