package br.edu.ufape.lanchonete.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FuncionarioRequestDTO {
    private String cpf;
    private String nome;
    private String cargo;
    private LocalDate dataAdmissao;
    private BigDecimal salario;
    private String cpfGerente; 

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public LocalDate getDataAdmissao() { return dataAdmissao; }
    public void setDataAdmissao(LocalDate dataAdmissao) { this.dataAdmissao = dataAdmissao; }

    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }

    public String getCpfGerente() { return cpfGerente; }
    public void setCpfGerente(String cpfGerente) { this.cpfGerente = cpfGerente; }
}