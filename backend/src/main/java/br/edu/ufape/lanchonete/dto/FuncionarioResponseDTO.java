package br.edu.ufape.lanchonete.dto;

import br.edu.ufape.lanchonete.model.Funcionario;
import java.math.BigDecimal;
import java.time.LocalDate;

public class FuncionarioResponseDTO {
    private String cpf;
    private String nome;
    private String cargo;
    private LocalDate dataAdmissao;
    private BigDecimal salario;
    private String cpfGerente;

    public FuncionarioResponseDTO() {}

    public FuncionarioResponseDTO(Funcionario funcionario) {
        this.cpf = funcionario.getCpf();
        this.nome = funcionario.getNome();
        this.cargo = funcionario.getCargo();
        this.dataAdmissao = funcionario.getDataAdmissao();
        this.salario = funcionario.getSalario();
        
        if (funcionario.getGerente() != null) {
            this.cpfGerente = funcionario.getGerente().getCpf();
        }
    }

    // Getters
    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public String getCargo() { return cargo; }
    public LocalDate getDataAdmissao() { return dataAdmissao; }
    public BigDecimal getSalario() { return salario; }
    public String getCpfGerente() { return cpfGerente; }
}