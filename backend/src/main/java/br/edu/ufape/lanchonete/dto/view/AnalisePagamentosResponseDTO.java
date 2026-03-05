package br.edu.ufape.lanchonete.dto.view;

import java.math.BigDecimal;

import br.edu.ufape.lanchonete.model.view.VwAnalisePagamentos;

public class AnalisePagamentosResponseDTO {

    private String metodoPagamento;
    private Long totalTransacoes;
    private BigDecimal valorTotal;
    private BigDecimal ticketMedio;

    public AnalisePagamentosResponseDTO(VwAnalisePagamentos view) {
        this.metodoPagamento = view.getMetodoPagamento();
        this.totalTransacoes = view.getTotalTransacoes();
        this.valorTotal = view.getValorTotal();
        this.ticketMedio = view.getTicketMedio();
    }

    // Getters
    public String getMetodoPagamento() { return metodoPagamento; }
    public Long getTotalTransacoes() { return totalTransacoes; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public BigDecimal getTicketMedio() { return ticketMedio; }
}
