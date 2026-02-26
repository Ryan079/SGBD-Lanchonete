package br.edu.ufape.lanchonete.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Funcionario {

    @Id
    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 50, nullable = false)
    private String cargo;

    @Column(name = "data_admissao", nullable = false)
    private LocalDate dataAdmissao;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal salario;

    @ManyToOne
    @JoinColumn(name = "cpf_gerente")
    private Funcionario gerente;

    public Funcionario() {}

    public Funcionario(String cpf, String nome, String cargo, LocalDate dataAdmissao, BigDecimal salario, Funcionario gerente) {
        this.cpf = cpf;
        this.nome = nome;
        this.cargo = cargo;
        this.dataAdmissao = dataAdmissao;
        this.salario = salario;
        this.gerente = gerente;
    }

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

    public Funcionario getGerente() { return gerente; }
    public void setGerente(Funcionario gerente) { this.gerente = gerente; }
}