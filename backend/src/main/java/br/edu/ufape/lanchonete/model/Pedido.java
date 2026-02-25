package br.edu.ufape.lanchonete.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido", nullable = false)
    private Integer idPedido; // Código único da venda

    @Column(name = "data_hora", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime dataHora; // Momento da abertura do pedido

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal = BigDecimal.ZERO; // Valor final a ser pago

    @Column(name = "endereco_entrega", columnDefinition = "TEXT")
    private String enderecoEntrega; // Para pedidos delivery

    @Column(name = "ponto_referencia", columnDefinition = "TEXT")
    private String pontoReferencia; // Usado se for delivery

    @Column(name = "taxa_entrega", precision = 10, scale = 2)
    private BigDecimal taxaEntrega = BigDecimal.ZERO; // Custo do frete, apenas delivery

    @Column(name = "troco_para", precision = 10, scale = 2)
    private BigDecimal trocoPara = BigDecimal.ZERO;

    @Column(length = 20)
    private String situacao; // Pendente, Em Preparo, Entregue, Cancelado

    //Vários pedidos podem pertencer a um mesmo Cliente
    @ManyToOne
    @JoinColumn(name = "cpf_cliente", nullable = false)
    private Cliente cliente;

    public Pedido() {}

    // Getters e Setters
    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public String getEnderecoEntrega() { return enderecoEntrega; }
    public void setEnderecoEntrega(String enderecoEntrega) { this.enderecoEntrega = enderecoEntrega; }

    public String getPontoReferencia() { return pontoReferencia; }
    public void setPontoReferencia(String pontoReferencia) { this.pontoReferencia = pontoReferencia; }

    public BigDecimal getTaxaEntrega() { return taxaEntrega; }
    public void setTaxaEntrega(BigDecimal taxaEntrega) { this.taxaEntrega = taxaEntrega; }

    public BigDecimal getTrocoPara() { return trocoPara; }
    public void setTrocoPara(BigDecimal trocoPara) { this.trocoPara = trocoPara; }

    public String getSituacao() { return situacao; }
    public void setSituacao(String situacao) { this.situacao = situacao; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}