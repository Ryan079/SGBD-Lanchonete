/**
 * Modelos relacionados aos Relatórios e Dashboard
 */

export interface FaturamentoDiario {
  data: string;
  totalPedidos: number;
  faturamentoTotal: number;
  ticketMedio: number;
  ticketMinimo: number;
  ticketMaximo: number;
}

export interface ClienteFrequente {
  cpf: string;
  cliente: string;
  telefone: string;
  totalPedidos: number;
  valorTotalGasto: number;
  ticketMedio: number;
}

export interface HorarioPico {
  hora: number;
  quantidadePedidos: number;
  ticketMedio: number;
  faturamentoTotal: number;
}

export interface AnalisePagamento {
  metodoPagamento: string;
  totalTransacoes: number;
  valorTotal: number;
  ticketMedio: number;
}

export interface ProdutoBaixaRotatividade {
  idCardapio: number;
  produto: string;
  categoria: string;
  preco: number;
  vendasUltimos30Dias: number;
  receitaUltimos30Dias: number;
}

export interface DashboardExecutivo {
  pedidosHoje: number;
  faturamentoHoje: number;
  produtosEstoqueCritico: number;
  clientesAtendidosHoje: number;
  pedidosEmAberto: number;
  ticketMedioMes: number;
  faturamentoMes: number;
}

export interface RankingCategoria {
  categoria: string;
  totalProdutos: number;
  totalUnidadesVendidas: number;
  receitaTotal: number;
  precoMedio: number;
}

export type TipoRelatorio = 
  | 'faturamento-diario'
  | 'clientes-frequentes'
  | 'horarios-pico'
  | 'analise-pagamentos'
  | 'produtos-baixa-rotatividade'
  | 'dashboard-executivo'
  | 'ranking-categorias';

export type FormatoExportacao = 'pdf' | 'csv';
