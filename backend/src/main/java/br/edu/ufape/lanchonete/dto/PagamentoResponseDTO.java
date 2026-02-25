package br.edu.ufape.lanchonete.dto;
import br.edu.ufape.lanchonete.model.Pagamento;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagamentoResponseDTO {
    private Integer idPagamento;
    private Integer idPedido;
    private BigDecimal valorPago;
    private String metodoPagamento;
    private LocalDateTime datahoraPagamento;

    public PagamentoResponseDTO() {}
    public PagamentoResponseDTO(Pagamento p) {
        this.idPagamento = p.getIdPagamento();
        this.idPedido = p.getPedido().getIdPedido();
        this.valorPago = p.getValorPago();
        this.metodoPagamento = p.getMetodoPagamento();
        this.datahoraPagamento = p.getDatahoraPagamento();
    }

    public Integer getIdPagamento() { return idPagamento; }
    public Integer getIdPedido() { return idPedido; }
    public BigDecimal getValorPago() { return valorPago; }
    public String getMetodoPagamento() { return metodoPagamento; }
    public LocalDateTime getDatahoraPagamento() { return datahoraPagamento; }
}