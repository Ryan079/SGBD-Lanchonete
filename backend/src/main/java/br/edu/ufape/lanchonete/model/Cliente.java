package br.edu.ufape.lanchonete.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cliente {

    @Id
    @Column(length = 11, nullable = false)
    private String cpf; 

    @Column(length = 100, nullable = false)
    private String nome; 

    @Column(columnDefinition = "TEXT")
    private String email; 

    @Column(length = 20)
    private String telefone; 

    @Column(columnDefinition = "TEXT")
    private String endereco;
    
    public Cliente() {}

    public Cliente(String cpf, String nome, String email, String telefone, String endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}