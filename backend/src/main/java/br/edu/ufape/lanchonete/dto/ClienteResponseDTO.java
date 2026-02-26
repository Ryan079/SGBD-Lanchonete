package br.edu.ufape.lanchonete.dto;

import br.edu.ufape.lanchonete.model.Cliente;

public class ClienteResponseDTO {
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;

    public ClienteResponseDTO() {}

    public ClienteResponseDTO(Cliente cliente) {
        this.cpf = cliente.getCpf();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
        this.endereco = cliente.getEndereco();
    }

    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getEndereco() { return endereco; }
}