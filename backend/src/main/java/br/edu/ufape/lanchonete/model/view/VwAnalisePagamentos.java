package br.edu.ufape.lanchonete.model.view;

import java.math.BigDecimal;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade read-only mapeada para a view vw_analise_pagamentos.
 * Distribuição de vendas por forma de pagamento.
 * Nenhuma operação de escrita é permitida (@Immutable).
 */
@Entity
@Immutable
@Table(name = "vw_analise_pagamentos")
public class VwAnalisePagamentos {

    @Id
    @Column(name = "metodo_pagamento", length = 50)
    private String metodoPagamento;

    @Column(name = "total_transacoes")
    private Long totalTransacoes;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "ticket_medio", precision = 10, scale = 2)
    private BigDecimal ticketMedio;

    // Construtores
    public VwAnalisePagamentos() {
    }

    // Getters e Setters
    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Long getTotalTransacoes() {
        return totalTransacoes;
    }

    public void setTotalTransacoes(Long totalTransacoes) {
        this.totalTransacoes = totalTransacoes;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getTicketMedio() {
        return ticketMedio;
    }

    public void setTicketMedio(BigDecimal ticketMedio) {
        this.ticketMedio = ticketMedio;
    }
}
