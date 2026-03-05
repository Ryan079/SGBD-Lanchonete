package br.edu.ufape.lanchonete.model.view;

import java.math.BigDecimal;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade read-only mapeada para a view vw_dashboard_executivo.
 * Métricas principais do negócio em tempo real (KPIs consolidados).
 * Nenhuma operação de escrita é permitida (@Immutable).
 */
@Entity
@Immutable
@Table(name = "vw_dashboard_executivo")
public class VwDashboardExecutivo {

    // ID sintético — a view sempre retorna uma única linha com id = 1
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "pedidos_hoje")
    private Long pedidosHoje;

    @Column(name = "faturamento_hoje", precision = 10, scale = 2)
    private BigDecimal faturamentoHoje;

    @Column(name = "produtos_estoque_critico")
    private Long produtosEstoqueCritico;

    @Column(name = "clientes_atendidos_hoje")
    private Long clientesAtendidosHoje;

    @Column(name = "pedidos_em_aberto")
    private Long pedidosEmAberto;

    @Column(name = "ticket_medio_mes", precision = 10, scale = 2)
    private BigDecimal ticketMedioMes;

    @Column(name = "faturamento_mes", precision = 10, scale = 2)
    private BigDecimal faturamentoMes;

    // Construtores
    public VwDashboardExecutivo() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPedidosHoje() {
        return pedidosHoje;
    }

    public void setPedidosHoje(Long pedidosHoje) {
        this.pedidosHoje = pedidosHoje;
    }

    public BigDecimal getFaturamentoHoje() {
        return faturamentoHoje;
    }

    public void setFaturamentoHoje(BigDecimal faturamentoHoje) {
        this.faturamentoHoje = faturamentoHoje;
    }

    public Long getProdutosEstoqueCritico() {
        return produtosEstoqueCritico;
    }

    public void setProdutosEstoqueCritico(Long produtosEstoqueCritico) {
        this.produtosEstoqueCritico = produtosEstoqueCritico;
    }

    public Long getClientesAtendidosHoje() {
        return clientesAtendidosHoje;
    }

    public void setClientesAtendidosHoje(Long clientesAtendidosHoje) {
        this.clientesAtendidosHoje = clientesAtendidosHoje;
    }

    public Long getPedidosEmAberto() {
        return pedidosEmAberto;
    }

    public void setPedidosEmAberto(Long pedidosEmAberto) {
        this.pedidosEmAberto = pedidosEmAberto;
    }

    public BigDecimal getTicketMedioMes() {
        return ticketMedioMes;
    }

    public void setTicketMedioMes(BigDecimal ticketMedioMes) {
        this.ticketMedioMes = ticketMedioMes;
    }

    public BigDecimal getFaturamentoMes() {
        return faturamentoMes;
    }

    public void setFaturamentoMes(BigDecimal faturamentoMes) {
        this.faturamentoMes = faturamentoMes;
    }
}
