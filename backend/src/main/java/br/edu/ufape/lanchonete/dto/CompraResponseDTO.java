package br.edu.ufape.lanchonete.dto;
import br.edu.ufape.lanchonete.model.Compra;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CompraResponseDTO {
    private Integer idCompra;
    private LocalDateTime dataCompra;
    private BigDecimal valorTotalCompra;
    private String cnpjFornecedor;

    public CompraResponseDTO() {}
    public CompraResponseDTO(Compra c) {
        this.idCompra = c.getIdCompra();
        this.dataCompra = c.getDataCompra();
        this.valorTotalCompra = c.getValorTotalCompra();
        this.cnpjFornecedor = c.getFornecedor().getCnpj();
    }

    public Integer getIdCompra() { return idCompra; }
    public LocalDateTime getDataCompra() { return dataCompra; }
    public BigDecimal getValorTotalCompra() { return valorTotalCompra; }
    public String getCnpjFornecedor() { return cnpjFornecedor; }
}