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
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento", nullable = false)
    private Integer idPagamento; 

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido; 

    @Column(name = "valor_pago", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorPago; 

    @Column(name = "metodo_pagamento", length = 50, nullable = false)
    private String metodoPagamento; 

    @Column(name = "datahora_pagamento", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime datahoraPagamento; 

    public Pagamento() {}

    public Integer getIdPagamento() { return idPagamento; }
    public void setIdPagamento(Integer idPagamento) { this.idPagamento = idPagamento; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public BigDecimal getValorPago() { return valorPago; }
    public void setValorPago(BigDecimal valorPago) { this.valorPago = valorPago; }

    public String getMetodoPagamento() { return metodoPagamento; }
    public void setMetodoPagamento(String metodoPagamento) { this.metodoPagamento = metodoPagamento; }

    public LocalDateTime getDatahoraPagamento() { return datahoraPagamento; }
    public void setDatahoraPagamento(LocalDateTime datahoraPagamento) { this.datahoraPagamento = datahoraPagamento; }
}