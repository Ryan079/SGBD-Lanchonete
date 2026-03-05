package br.edu.ufape.lanchonete.dto.view;

import java.math.BigDecimal;

import br.edu.ufape.lanchonete.model.view.VwRankingCategorias;

public class RankingCategoriasResponseDTO {

    private String categoria;
    private Long totalProdutos;
    private Long totalUnidadesVendidas;
    private BigDecimal receitaTotal;
    private BigDecimal precoMedio;

    public RankingCategoriasResponseDTO(VwRankingCategorias view) {
        this.categoria = view.getCategoria();
        this.totalProdutos = view.getTotalProdutos();
        this.totalUnidadesVendidas = view.getTotalUnidadesVendidas();
        this.receitaTotal = view.getReceitaTotal();
        this.precoMedio = view.getPrecoMedio();
    }

    // Getters
    public String getCategoria() { return categoria; }
    public Long getTotalProdutos() { return totalProdutos; }
    public Long getTotalUnidadesVendidas() { return totalUnidadesVendidas; }
    public BigDecimal getReceitaTotal() { return receitaTotal; }
    public BigDecimal getPrecoMedio() { return precoMedio; }
}
