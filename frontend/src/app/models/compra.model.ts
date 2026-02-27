/**
 * Modelos relacionados a Compra
 */

export interface ItemCompraRequest {
  idProduto: number;
  quantidade: number;
  valorUnitario: number;
  lote: string;
}

export interface CompraRequest {
  cnpjFornecedor: string;
  itens: ItemCompraRequest[];
}

export interface CompraResponse {
  idCompra: number;
  dataCompra: string;
  valorTotalCompra: number;
  cnpjFornecedor: string;
}
