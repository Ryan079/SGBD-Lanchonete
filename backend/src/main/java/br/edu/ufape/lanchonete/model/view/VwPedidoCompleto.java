package br.edu.ufape.lanchonete.model.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade read-only mapeada para a view vw_pedido_completo.
 * Consolida Pedido + Cliente + ItemPedido + Cardapio + Pagamento.
 * Nenhuma operação de escrita é permitida (@Immutable).
 */
@Entity
@Immutable
@Table(name = "vw_pedido_completo")
public class VwPedidoCompleto {


    @Id
    @Column(name = "id_item_pedido")
    private Integer idItemPedido;

    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "situacao", length = 20)
    private String situacao;

    @Column(name = "endereco_entrega", columnDefinition = "TEXT")
    private String enderecoEntrega;

    @Column(name = "taxa_entrega", precision = 10, scale = 2)
    private BigDecimal taxaEntrega;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;


    @Column(name = "cpf_cliente", length = 11)
    private String cpfCliente;

    @Column(name = "nome_cliente", length = 100)
    private String nomeCliente;

    @Column(name = "telefone_cliente", length = 20)
    private String telefoneCliente;


    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "valor_unitario", precision = 10, scale = 2)
    private BigDecimal valorUnitario;

    @Column(name = "subtotal_item", precision = 10, scale = 2)
    private BigDecimal subtotalItem;


    @Column(name = "id_cardapio")
    private Integer idCardapio;

    @Column(name = "nome_produto", length = 100)
    private String nomeProduto;

    @Column(name = "categoria_produto", length = 20)
    private String categoriaProduto;


    @Column(name = "id_pagamento")
    private Integer idPagamento;

    @Column(name = "metodo_pagamento", length = 50)
    private String metodoPagamento;

    @Column(name = "valor_pago", precision = 10, scale = 2)
    private BigDecimal valorPago;

    @Column(name = "datahora_pagamento")
    private LocalDateTime datahoraPagamento;

    public VwPedidoCompleto() {}


    public Integer getIdItemPedido() { return idItemPedido; }
    public Integer getIdPedido() { return idPedido; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getSituacao() { return situacao; }
    public String getEnderecoEntrega() { return enderecoEntrega; }
    public BigDecimal getTaxaEntrega() { return taxaEntrega; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public String getCpfCliente() { return cpfCliente; }
    public String getNomeCliente() { return nomeCliente; }
    public String getTelefoneCliente() { return telefoneCliente; }
    public Integer getQuantidade() { return quantidade; }
    public BigDecimal getValorUnitario() { return valorUnitario; }
    public BigDecimal getSubtotalItem() { return subtotalItem; }
    public Integer getIdCardapio() { return idCardapio; }
    public String getNomeProduto() { return nomeProduto; }
    public String getCategoriaProduto() { return categoriaProduto; }
    public Integer getIdPagamento() { return idPagamento; }
    public String getMetodoPagamento() { return metodoPagamento; }
    public BigDecimal getValorPago() { return valorPago; }
    public LocalDateTime getDatahoraPagamento() { return datahoraPagamento; }
}
