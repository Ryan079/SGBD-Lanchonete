package br.edu.ufape.lanchonete.dto;
import br.edu.ufape.lanchonete.model.Fornecedor;
import java.time.LocalDate;

public class FornecedorResponseDTO {
    private String cnpj;
    private String nomeEmpresa;
    private LocalDate dataCadastro;
    private String telefone;

    public FornecedorResponseDTO() {}
    public FornecedorResponseDTO(Fornecedor f) {
        this.cnpj = f.getCnpj();
        this.nomeEmpresa = f.getNomeEmpresa();
        this.dataCadastro = f.getDataCadastro();
        this.telefone = f.getTelefone();
    }
    public String getCnpj() { return cnpj; }
    public String getNomeEmpresa() { return nomeEmpresa; }
    public LocalDate getDataCadastro() { return dataCadastro; }
    public String getTelefone() { return telefone; }
}