package br.edu.ufape.lanchonete.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Fornecedor {

    @Id
    @Column(length = 14, nullable = false)
    private String cnpj;

    @Column(name = "nome_empresa", length = 100, nullable = false)
    private String nomeEmpresa;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(length = 20, nullable = false)
    private String telefone;

    public Fornecedor() {}

    public Fornecedor(String cnpj, String nomeEmpresa, LocalDate dataCadastro, String telefone) {
        this.cnpj = cnpj;
        this.nomeEmpresa = nomeEmpresa;
        this.dataCadastro = dataCadastro;
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }

    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}