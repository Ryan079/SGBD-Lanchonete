package br.edu.ufape.lanchonete.dto;

public class ItemPedidoRequestDTO {
    private Integer idCardapio;
    private Integer quantidade;

    public Integer getIdCardapio() { return idCardapio; }
    public void setIdCardapio(Integer idCardapio) { this.idCardapio = idCardapio; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}