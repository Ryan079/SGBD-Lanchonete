package br.edu.ufape.lanchonete.model.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Immutable
@Table(name = "vw_clientes_frequentes")
public class VwClientesFrequentes {

    @Id
    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "cliente", length = 100)
    private String cliente;

    @Column(name = "telefone", length = 15)
    private String telefone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "total_pedidos")
    private Long totalPedidos;

    @Column(name = "valor_total_gasto", precision = 10, scale = 2)
    private BigDecimal valorTotalGasto;

    @Column(name = "ticket_medio", precision = 10, scale = 2)
    private BigDecimal ticketMedio;

    @Column(name = "ultima_compra")
    private LocalDateTime ultimaCompra;

    @Column(name = "primeira_compra")
    private LocalDateTime primeiraCompra;

    // Construtores
    public VwClientesFrequentes() {
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(Long totalPedidos) {
        this.totalPedidos = totalPedidos;
    }

    public BigDecimal getValorTotalGasto() {
        return valorTotalGasto;
    }

    public void setValorTotalGasto(BigDecimal valorTotalGasto) {
        this.valorTotalGasto = valorTotalGasto;
    }

    public BigDecimal getTicketMedio() {
        return ticketMedio;
    }

    public void setTicketMedio(BigDecimal ticketMedio) {
        this.ticketMedio = ticketMedio;
    }

    public LocalDateTime getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(LocalDateTime ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    public LocalDateTime getPrimeiraCompra() {
        return primeiraCompra;
    }

    public void setPrimeiraCompra(LocalDateTime primeiraCompra) {
        this.primeiraCompra = primeiraCompra;
    }
}
