package br.edu.ufape.lanchonete.dto.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.edu.ufape.lanchonete.model.view.VwPedidoCompleto;

public class PedidoCompletoResponseDTO {

    private Integer idPedido;
    private LocalDateTime dataHora;
    private String situacao;

    private String nomeCliente;
    private String telefoneCliente;

    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal subtotalItem;

    private String metodoPagamento;
    private BigDecimal valorPago;

    public PedidoCompletoResponseDTO(VwPedidoCompleto view) {
        this.idPedido = view.getIdPedido();
        this.dataHora = view.getDataHora();
        this.situacao = view.getSituacao();
        this.nomeCliente = view.getNomeCliente();
        this.telefoneCliente = view.getTelefoneCliente();
        this.nomeProduto = view.getNomeProduto();
        this.quantidade = view.getQuantidade();
        this.subtotalItem = view.getSubtotalItem();
        this.metodoPagamento = view.getMetodoPagamento();
        this.valorPago = view.getValorPago();
    }

    public Integer getIdPedido() { return idPedido; }

    public LocalDateTime getDataHora() { return dataHora; }

    public String getSituacao() { return situacao; }

    public String getNomeCliente() { return nomeCliente; }

    public String getTelefoneCliente() { return telefoneCliente; }

    public String getNomeProduto() { return nomeProduto; }

    public Integer getQuantidade() { return quantidade; }

    public BigDecimal getSubtotalItem() { return subtotalItem; }

    public String getMetodoPagamento() { return metodoPagamento; }

    public BigDecimal getValorPago() { return valorPago; }
    
}
