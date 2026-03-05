package br.edu.ufape.lanchonete.model.view;

import java.math.BigDecimal;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade read-only mapeada para a view vw_ranking_categorias.
 * Desempenho de vendas por categoria de produto.
 * Nenhuma operação de escrita é permitida (@Immutable).
 */
@Entity
@Immutable
@Table(name = "vw_ranking_categorias")
public class VwRankingCategorias {

    @Id
    @Column(name = "categoria", length = 50)
    private String categoria;

    @Column(name = "total_produtos")
    private Long totalProdutos;

    @Column(name = "total_unidades_vendidas")
    private Long totalUnidadesVendidas;

    @Column(name = "receita_total", precision = 10, scale = 2)
    private BigDecimal receitaTotal;

    @Column(name = "preco_medio", precision = 10, scale = 2)
    private BigDecimal precoMedio;

    // Construtores
    public VwRankingCategorias() {
    }

    // Getters e Setters
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getTotalProdutos() {
        return totalProdutos;
    }

    public void setTotalProdutos(Long totalProdutos) {
        this.totalProdutos = totalProdutos;
    }

    public Long getTotalUnidadesVendidas() {
        return totalUnidadesVendidas;
    }

    public void setTotalUnidadesVendidas(Long totalUnidadesVendidas) {
        this.totalUnidadesVendidas = totalUnidadesVendidas;
    }

    public BigDecimal getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(BigDecimal receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public BigDecimal getPrecoMedio() {
        return precoMedio;
    }

    public void setPrecoMedio(BigDecimal precoMedio) {
        this.precoMedio = precoMedio;
    }
}
