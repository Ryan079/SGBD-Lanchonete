package br.edu.ufape.lanchonete.dto.view;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.edu.ufape.lanchonete.model.view.VwFaturamentoDiario;

public class FaturamentoDiarioResponseDTO {

    private LocalDate data;
    private Long totalPedidos;
    private BigDecimal faturamentoTotal;
    private BigDecimal ticketMedio;
    private BigDecimal ticketMinimo;
    private BigDecimal ticketMaximo;

    public FaturamentoDiarioResponseDTO(VwFaturamentoDiario view) {
        this.data = view.getData();
        this.totalPedidos = view.getTotalPedidos();
        this.faturamentoTotal = view.getFaturamentoTotal();
        this.ticketMedio = view.getTicketMedio();
        this.ticketMinimo = view.getTicketMinimo();
        this.ticketMaximo = view.getTicketMaximo();
    }

    // Getters
    public LocalDate getData() { return data; }
    public Long getTotalPedidos() { return totalPedidos; }
    public BigDecimal getFaturamentoTotal() { return faturamentoTotal; }
    public BigDecimal getTicketMedio() { return ticketMedio; }
    public BigDecimal getTicketMinimo() { return ticketMinimo; }
    public BigDecimal getTicketMaximo() { return ticketMaximo; }
}
