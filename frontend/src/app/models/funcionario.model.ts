/**
 * Modelos relacionados a Funcionário
 */

export interface FuncionarioRequest {
  cpf: string;
  nome: string;
  cargo: string;
  dataAdmissao: string; // ISO date string
  salario: number;
  cpfGerente?: string;
}

export interface FuncionarioResponse {
  cpf: string;
  nome: string;
  cargo: string;
  dataAdmissao: string;
  salario: number;
  cpfGerente?: string;
}
