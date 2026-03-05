package br.edu.ufape.lanchonete.dto.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.edu.ufape.lanchonete.model.view.VwClientesFrequentes;

public class ClientesFrequentesResponseDTO {

    private String cpf;
    private String cliente;
    private String telefone;
    private String email;
    private Long totalPedidos;
    private BigDecimal valorTotalGasto;
    private BigDecimal ticketMedio;
    private LocalDateTime ultimaCompra;
    private LocalDateTime primeiraCompra;

    public ClientesFrequentesResponseDTO(VwClientesFrequentes view) {
        this.cpf = view.getCpf();
        this.cliente = view.getCliente();
        this.telefone = view.getTelefone();
        this.email = view.getEmail();
        this.totalPedidos = view.getTotalPedidos();
        this.valorTotalGasto = view.getValorTotalGasto();
        this.ticketMedio = view.getTicketMedio();
        this.ultimaCompra = view.getUltimaCompra();
        this.primeiraCompra = view.getPrimeiraCompra();
    }

    // Getters
    public String getCpf() { return cpf; }
    public String getCliente() { return cliente; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public Long getTotalPedidos() { return totalPedidos; }
    public BigDecimal getValorTotalGasto() { return valorTotalGasto; }
    public BigDecimal getTicketMedio() { return ticketMedio; }
    public LocalDateTime getUltimaCompra() { return ultimaCompra; }
    public LocalDateTime getPrimeiraCompra() { return primeiraCompra; }
}
