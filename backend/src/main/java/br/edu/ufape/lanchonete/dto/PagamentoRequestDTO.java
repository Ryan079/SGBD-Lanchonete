package br.edu.ufape.lanchonete.dto;
import java.math.BigDecimal;

public class PagamentoRequestDTO {
    private Integer idPedido;
    private BigDecimal valorPago;
    private String metodoPagamento;

    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }
    public BigDecimal getValorPago() { return valorPago; }
    public void setValorPago(BigDecimal valorPago) { this.valorPago = valorPago; }
    public String getMetodoPagamento() { return metodoPagamento; }
    public void setMetodoPagamento(String metodoPagamento) { this.metodoPagamento = metodoPagamento; }
}