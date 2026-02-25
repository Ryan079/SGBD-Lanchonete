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
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_pedido", nullable = false)
    private Integer idItemPedido; // Identificador único do item no pedido

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido; // Vínculo com o pedido

    @ManyToOne
    @JoinColumn(name = "id_cardapio", nullable = false)
    private Cardapio cardapio; // Referência ao produto escolhido do cardápio

    @Column(nullable = false)
    private Integer quantidade = 1; // Quantidade do item que foi pedido

    @Column(name = "valor_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorUnitario; // Preço congelado no momento da venda

    public ItemPedido() {}

    // Getters e Setters
    public Integer getIdItemPedido() { return idItemPedido; }
    public void setIdItemPedido(Integer idItemPedido) { this.idItemPedido = idItemPedido; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Cardapio getCardapio() { return cardapio; }
    public void setCardapio(Cardapio cardapio) { this.cardapio = cardapio; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public BigDecimal getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(BigDecimal valorUnitario) { this.valorUnitario = valorUnitario; }
}