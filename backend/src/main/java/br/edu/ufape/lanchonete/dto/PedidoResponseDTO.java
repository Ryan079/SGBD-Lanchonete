package br.edu.ufape.lanchonete.dto;

import br.edu.ufape.lanchonete.model.Pedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoResponseDTO {
    private Integer idPedido;
    private LocalDateTime dataHora;
    private BigDecimal valorTotal;
    private String situacao;
    private String cpfCliente;

    public PedidoResponseDTO() {}

    public PedidoResponseDTO(Pedido pedido) {
        this.idPedido = pedido.getIdPedido();
        this.dataHora = pedido.getDataHora();
        this.valorTotal = pedido.getValorTotal();
        this.situacao = pedido.getSituacao();
        this.cpfCliente = pedido.getCliente().getCpf();
    }

    // Getters
    public Integer getIdPedido() { return idPedido; }
    public LocalDateTime getDataHora() { return dataHora; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public String getSituacao() { return situacao; }
    public String getCpfCliente() { return cpfCliente; }
}