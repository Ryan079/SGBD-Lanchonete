package br.edu.ufape.lanchonete.dto.view;

import java.math.BigDecimal;

import br.edu.ufape.lanchonete.model.view.VwHorariosPico;

public class HorariosPicoResponseDTO {

    private Integer hora;
    private Long quantidadePedidos;
    private BigDecimal ticketMedio;
    private BigDecimal faturamentoTotal;

    public HorariosPicoResponseDTO(VwHorariosPico view) {
        this.hora = view.getHora() != null ? view.getHora().intValue() : null;
        this.quantidadePedidos = view.getQuantidadePedidos();
        this.ticketMedio = view.getTicketMedio();
        this.faturamentoTotal = view.getFaturamentoTotal();
    }

    // Getters
    public Integer getHora() { return hora; }
    public Long getQuantidadePedidos() { return quantidadePedidos; }
    public BigDecimal getTicketMedio() { return ticketMedio; }
    public BigDecimal getFaturamentoTotal() { return faturamentoTotal; }
}
