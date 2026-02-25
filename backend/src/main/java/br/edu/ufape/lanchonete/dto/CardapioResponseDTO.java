package br.edu.ufape.lanchonete.dto;
import br.edu.ufape.lanchonete.model.Cardapio;
import java.math.BigDecimal;

public class CardapioResponseDTO {
    private Integer idCardapio;
    private String nome;
    private String categoria;
    private String descricao;
    private BigDecimal preco;

    public CardapioResponseDTO() {}
    public CardapioResponseDTO(Cardapio c) {
        this.idCardapio = c.getIdCardapio();
        this.nome = c.getNome();
        this.categoria = c.getCategoria();
        this.descricao = c.getDescricao();
        this.preco = c.getPreco();
    }
    public Integer getIdCardapio() { return idCardapio; }
    public String getNome() { return nome; }
    public String getCategoria() { return categoria; }
    public String getDescricao() { return descricao; }
    public BigDecimal getPreco() { return preco; }
}