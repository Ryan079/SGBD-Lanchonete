package br.edu.ufape.lanchonete.model.view;

import java.math.BigDecimal;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade read-only mapeada para a view vw_horarios_pico.
 * Distribuição de pedidos por hora do dia.
 * Nenhuma operação de escrita é permitida (@Immutable).
 */
@Entity
@Immutable
@Table(name = "vw_horarios_pico")
public class VwHorariosPico {

    @Id
    @Column(name = "hora")
    private Double hora;

    @Column(name = "quantidade_pedidos")
    private Long quantidadePedidos;

    @Column(name = "ticket_medio", precision = 10, scale = 2)
    private BigDecimal ticketMedio;

    @Column(name = "faturamento_total", precision = 10, scale = 2)
    private BigDecimal faturamentoTotal;

    // Construtores
    public VwHorariosPico() {
    }

    // Getters e Setters
    public Double getHora() {
        return hora;
    }

    public void setHora(Double hora) {
        this.hora = hora;
    }

    public Long getQuantidadePedidos() {
        return quantidadePedidos;
    }

    public void setQuantidadePedidos(Long quantidadePedidos) {
        this.quantidadePedidos = quantidadePedidos;
    }

    public BigDecimal getTicketMedio() {
        return ticketMedio;
    }

    public void setTicketMedio(BigDecimal ticketMedio) {
        this.ticketMedio = ticketMedio;
    }

    public BigDecimal getFaturamentoTotal() {
        return faturamentoTotal;
    }

    public void setFaturamentoTotal(BigDecimal faturamentoTotal) {
        this.faturamentoTotal = faturamentoTotal;
    }
}
