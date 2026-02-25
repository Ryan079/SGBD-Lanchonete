package br.edu.ufape.lanchonete.dto;

public class EstoqueRequestDTO {
    private String nome;
    private String unidadeMedida;
    private Integer qtdEstoqueAtual;
    private Integer qtdEstoqueMinimo;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }
    public Integer getQtdEstoqueAtual() { return qtdEstoqueAtual; }
    public void setQtdEstoqueAtual(Integer qtdEstoqueAtual) { this.qtdEstoqueAtual = qtdEstoqueAtual; }
    public Integer getQtdEstoqueMinimo() { return qtdEstoqueMinimo; }
    public void setQtdEstoqueMinimo(Integer qtdEstoqueMinimo) { this.qtdEstoqueMinimo = qtdEstoqueMinimo; }
}