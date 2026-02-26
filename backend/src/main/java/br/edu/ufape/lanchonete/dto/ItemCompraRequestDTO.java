package br.edu.ufape.lanchonete.dto;
import java.math.BigDecimal;

public class ItemCompraRequestDTO {
    private Integer idProduto;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private String lote;

    public Integer getIdProduto() { return idProduto; }
    public void setIdProduto(Integer idProduto) { this.idProduto = idProduto; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public BigDecimal getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(BigDecimal valorUnitario) { this.valorUnitario = valorUnitario; }
    public String getLote() { return lote; }
    public void setLote(String lote) { this.lote = lote; }
}