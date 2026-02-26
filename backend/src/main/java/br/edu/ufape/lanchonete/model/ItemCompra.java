package br.edu.ufape.lanchonete.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_compra")
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_compra", nullable = false)
    private Integer idItemCompra; 

    @ManyToOne
    @JoinColumn(name = "id_compra", nullable = false)
    private Compra compra; 

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Estoque produto; 

    @Column(nullable = false)
    private Integer quantidade; 

    @Column(name = "valor_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorUnitario; 

    @Column(length = 50, nullable = false)
    private String lote; 

    public ItemCompra() {}

    public Integer getIdItemCompra() { return idItemCompra; }
    public void setIdItemCompra(Integer idItemCompra) { this.idItemCompra = idItemCompra; }

    public Compra getCompra() { return compra; }
    public void setCompra(Compra compra) { this.compra = compra; }

    public Estoque getProduto() { return produto; }
    public void setProduto(Estoque produto) { this.produto = produto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public BigDecimal getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(BigDecimal valorUnitario) { this.valorUnitario = valorUnitario; }

    public String getLote() { return lote; }
    public void setLote(String lote) { this.lote = lote; }
}