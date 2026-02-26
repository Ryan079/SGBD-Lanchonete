package br.edu.ufape.lanchonete.dto;
import java.util.List;

public class CompraRequestDTO {
    private String cnpjFornecedor;
    private List<ItemCompraRequestDTO> itens;
    
    public String getCnpjFornecedor() { return cnpjFornecedor; }
    public void setCnpjFornecedor(String cnpjFornecedor) { this.cnpjFornecedor = cnpjFornecedor; }
    public List<ItemCompraRequestDTO> getItens() { return itens; }
    public void setItens(List<ItemCompraRequestDTO> itens) { this.itens = itens; }
}