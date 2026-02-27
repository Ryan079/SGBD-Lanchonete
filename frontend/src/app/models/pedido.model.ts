/**
 * Modelos relacionados a Pedido
 */

export interface ItemPedidoRequest {
  idCardapio: number;
  quantidade: number;
}

export interface PedidoRequest {
  cpfCliente: string;
  enderecoEntrega?: string;
  pontoReferencia?: string;
  taxaEntrega?: number;
  trocoPara?: number;
  itens: ItemPedidoRequest[];
}

export interface PedidoResponse {
  idPedido: number;
  dataHora: string;
  valorTotal: number;
  situacao: string;
  cpfCliente: string;
}

export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  last: boolean;
}
