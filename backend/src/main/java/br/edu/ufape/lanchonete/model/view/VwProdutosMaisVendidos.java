package br.edu.ufape.lanchonete.model.view;

import java.math.BigDecimal;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade read-only mapeada para a view vw_produtos_mais_vendidos.
 * Agrega quantidades vendidas e receita por produto do cardápio,
 * excluindo pedidos cancelados.
 * Nenhuma operação de escrita é permitida (@Immutable).
 */
@Entity
@Immutable
@Table(name = "vw_produtos_mais_vendidos")
public class VwProdutosMaisVendidos {

    @Id
    @Column(name = "id_cardapio")
    private Integer idCardapio;

    @Column(name = "nome_produto", length = 100)
    private String nomeProduto;

    @Column(name = "categoria", length = 20)
    private String categoria;

    @Column(name = "preco_unitario", precision = 10, scale = 2)
    private BigDecimal precoUnitario;

    @Column(name = "total_unidades_vendidas")
    private Long totalUnidadesVendidas;

    @Column(name = "receita_total", precision = 10, scale = 2)
    private BigDecimal receitaTotal;

    public VwProdutosMaisVendidos() {}


    public Integer getIdCardapio() { return idCardapio; }
    public String getNomeProduto() { return nomeProduto; }
    public String getCategoria() { return categoria; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public Long getTotalUnidadesVendidas() { return totalUnidadesVendidas; }
    public BigDecimal getReceitaTotal() { return receitaTotal; }
}
