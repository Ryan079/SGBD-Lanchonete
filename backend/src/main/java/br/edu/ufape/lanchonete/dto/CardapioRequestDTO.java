package br.edu.ufape.lanchonete.dto;
import java.math.BigDecimal;

public class CardapioRequestDTO {
    private String nome;
    private String categoria;
    private String descricao;
    private BigDecimal preco;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
}