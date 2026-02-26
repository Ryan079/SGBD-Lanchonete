package br.edu.ufape.lanchonete.dto.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.edu.ufape.lanchonete.model.view.VwPedidosEmAberto;

public class PedidoEmAbertoResponseDTO {

    private Integer idPedido;
    private LocalDateTime dataHora;
    private String situacao;
    private String nomeCliente;
    private String telefoneCliente;
    private BigDecimal valorTotal;
    private String enderecoEntrega;

    public PedidoEmAbertoResponseDTO(VwPedidosEmAberto view) {
        this.idPedido = view.getIdPedido();
        this.dataHora = view.getDataHora();
        this.situacao = view.getSituacao();
        this.nomeCliente = view.getNomeCliente();
        this.telefoneCliente = view.getTelefoneCliente();
        this.valorTotal = view.getValorTotal();
        this.enderecoEntrega = view.getEnderecoEntrega();
    }

    public Integer getIdPedido() { return idPedido; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getSituacao() { return situacao; }
    public String getNomeCliente() { return nomeCliente; }
    public String getTelefoneCliente() { return telefoneCliente; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public String getEnderecoEntrega() { return enderecoEntrega; }
    
}
