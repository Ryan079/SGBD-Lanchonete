package br.edu.ufape.lanchonete.dto.view;

import java.math.BigDecimal;

import br.edu.ufape.lanchonete.model.view.VwClientesFrequentes;

public class ClientesFrequentesResponseDTO {

    private String cpf;
    private String cliente;
    private String telefone;
    private Long totalPedidos;
    private BigDecimal valorTotalGasto;
    private BigDecimal ticketMedio;

    public ClientesFrequentesResponseDTO(VwClientesFrequentes view) {
        this.cpf = view.getCpf();
        this.cliente = view.getCliente();
        this.telefone = view.getTelefone();
        this.totalPedidos = view.getTotalPedidos();
        this.valorTotalGasto = view.getValorTotalGasto();
        this.ticketMedio = view.getTicketMedio();
    }

    // Getters
    public String getCpf() { return cpf; }
    public String getCliente() { return cliente; }
    public String getTelefone() { return telefone; }
    public Long getTotalPedidos() { return totalPedidos; }
    public BigDecimal getValorTotalGasto() { return valorTotalGasto; }
    public BigDecimal getTicketMedio() { return ticketMedio; }
}
