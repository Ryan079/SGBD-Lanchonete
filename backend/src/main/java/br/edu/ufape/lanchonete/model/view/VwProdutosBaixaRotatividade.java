package br.edu.ufape.lanchonete.model.view;

import java.math.BigDecimal;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade read-only mapeada para a view vw_produtos_baixa_rotatividade.
 * Identifica produtos que vendem pouco, auxiliando na gestão do cardápio.
 * Nenhuma operação de escrita é permitida (@Immutable).
 */
@Entity
@Immutable
@Table(name = "vw_produtos_baixa_rotatividade")
public class VwProdutosBaixaRotatividade {

    @Id
    @Column(name = "id_cardapio")
    private Integer idCardapio;

    @Column(name = "produto", length = 100)
    private String produto;

    @Column(name = "categoria", length = 50)
    private String categoria;

    @Column(name = "preco", precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "vendas_ultimos_30_dias")
    private Long vendasUltimos30Dias;

    @Column(name = "receita_ultimos_30_dias", precision = 10, scale = 2)
    private BigDecimal receitaUltimos30Dias;

    // Construtores
    public VwProdutosBaixaRotatividade() {
    }

    // Getters e Setters
    public Integer getIdCardapio() {
        return idCardapio;
    }

    public void setIdCardapio(Integer idCardapio) {
        this.idCardapio = idCardapio;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Long getVendasUltimos30Dias() {
        return vendasUltimos30Dias;
    }

    public void setVendasUltimos30Dias(Long vendasUltimos30Dias) {
        this.vendasUltimos30Dias = vendasUltimos30Dias;
    }

    public BigDecimal getReceitaUltimos30Dias() {
        return receitaUltimos30Dias;
    }

    public void setReceitaUltimos30Dias(BigDecimal receitaUltimos30Dias) {
        this.receitaUltimos30Dias = receitaUltimos30Dias;
    }
}
