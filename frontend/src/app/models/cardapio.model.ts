/**
 * Modelos relacionados a Cardápio
 */

export interface CardapioRequest {
  nome: string;
  categoria: 'Bebida' | 'Lanche' | 'Sobremesa';
  descricao?: string;
  preco: number;
}

export interface CardapioResponse {
  idCardapio: number;
  nome: string;
  categoria: string;
  descricao?: string;
  preco: number;
}
