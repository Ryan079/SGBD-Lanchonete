package br.edu.ufape.lanchonete.dto.view;

import java.math.BigDecimal;

import br.edu.ufape.lanchonete.model.view.VwProdutosBaixaRotatividade;

public class ProdutosBaixaRotatividadeResponseDTO {

    private Integer idCardapio;
    private String produto;
    private String categoria;
    private BigDecimal preco;
    private Long vendasUltimos30Dias;
    private BigDecimal receitaUltimos30Dias;

    public ProdutosBaixaRotatividadeResponseDTO(VwProdutosBaixaRotatividade view) {
        this.idCardapio = view.getIdCardapio();
        this.produto = view.getProduto();
        this.categoria = view.getCategoria();
        this.preco = view.getPreco();
        this.vendasUltimos30Dias = view.getVendasUltimos30Dias();
        this.receitaUltimos30Dias = view.getReceitaUltimos30Dias();
    }

    // Getters
    public Integer getIdCardapio() { return idCardapio; }
    public String getProduto() { return produto; }
    public String getCategoria() { return categoria; }
    public BigDecimal getPreco() { return preco; }
    public Long getVendasUltimos30Dias() { return vendasUltimos30Dias; }
    public BigDecimal getReceitaUltimos30Dias() { return receitaUltimos30Dias; }
}
