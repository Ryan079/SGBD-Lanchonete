package br.edu.ufape.lanchonete.dto.view;

import java.math.BigDecimal;

import br.edu.ufape.lanchonete.model.view.VwDashboardExecutivo;

public class DashboardExecutivoResponseDTO {

    private Long pedidosHoje;
    private BigDecimal faturamentoHoje;
    private Long produtosEstoqueCritico;
    private Long clientesAtendidosHoje;
    private Long pedidosEmAberto;
    private BigDecimal ticketMedioMes;
    private BigDecimal faturamentoMes;

    public DashboardExecutivoResponseDTO(VwDashboardExecutivo view) {
        this.pedidosHoje = view.getPedidosHoje();
        this.faturamentoHoje = view.getFaturamentoHoje();
        this.produtosEstoqueCritico = view.getProdutosEstoqueCritico();
        this.clientesAtendidosHoje = view.getClientesAtendidosHoje();
        this.pedidosEmAberto = view.getPedidosEmAberto();
        this.ticketMedioMes = view.getTicketMedioMes();
        this.faturamentoMes = view.getFaturamentoMes();
    }

    // Getters
    public Long getPedidosHoje() { return pedidosHoje; }
    public BigDecimal getFaturamentoHoje() { return faturamentoHoje; }
    public Long getProdutosEstoqueCritico() { return produtosEstoqueCritico; }
    public Long getClientesAtendidosHoje() { return clientesAtendidosHoje; }
    public Long getPedidosEmAberto() { return pedidosEmAberto; }
    public BigDecimal getTicketMedioMes() { return ticketMedioMes; }
    public BigDecimal getFaturamentoMes() { return faturamentoMes; }
}
