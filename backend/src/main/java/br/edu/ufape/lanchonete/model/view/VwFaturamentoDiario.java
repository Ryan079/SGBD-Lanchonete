package br.edu.ufape.lanchonete.model.view;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Immutable
@Table(name = "vw_faturamento_diario")
public class VwFaturamentoDiario {

    @Id
    @Column(name = "data")
    private LocalDate data;

    @Column(name = "total_pedidos")
    private Long totalPedidos;

    @Column(name = "faturamento_total", precision = 10, scale = 2)
    private BigDecimal faturamentoTotal;

    @Column(name = "ticket_medio", precision = 10, scale = 2)
    private BigDecimal ticketMedio;

    @Column(name = "ticket_minimo", precision = 10, scale = 2)
    private BigDecimal ticketMinimo;

    @Column(name = "ticket_maximo", precision = 10, scale = 2)
    private BigDecimal ticketMaximo;

    // Construtores
    public VwFaturamentoDiario() {
    }

    // Getters e Setters
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(Long totalPedidos) {
        this.totalPedidos = totalPedidos;
    }

    public BigDecimal getFaturamentoTotal() {
        return faturamentoTotal;
    }

    public void setFaturamentoTotal(BigDecimal faturamentoTotal) {
        this.faturamentoTotal = faturamentoTotal;
    }

    public BigDecimal getTicketMedio() {
        return ticketMedio;
    }

    public void setTicketMedio(BigDecimal ticketMedio) {
        this.ticketMedio = ticketMedio;
    }

    public BigDecimal getTicketMinimo() {
        return ticketMinimo;
    }

    public void setTicketMinimo(BigDecimal ticketMinimo) {
        this.ticketMinimo = ticketMinimo;
    }

    public BigDecimal getTicketMaximo() {
        return ticketMaximo;
    }

    public void setTicketMaximo(BigDecimal ticketMaximo) {
        this.ticketMaximo = ticketMaximo;
    }
}
