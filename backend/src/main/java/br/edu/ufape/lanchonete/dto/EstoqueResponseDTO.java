package br.edu.ufape.lanchonete.dto;
import br.edu.ufape.lanchonete.model.Estoque;
import java.time.LocalDateTime;

public class EstoqueResponseDTO {
    private Integer idProduto;
    private String nome;
    private String unidadeMedida;
    private Integer qtdEstoqueAtual;
    private Integer qtdEstoqueMinimo;
    private LocalDateTime dataUltimaAtualizacao;

    public EstoqueResponseDTO() {}
    public EstoqueResponseDTO(Estoque e) {
        this.idProduto = e.getIdProduto();
        this.nome = e.getNome();
        this.unidadeMedida = e.getUnidadeMedida();
        this.qtdEstoqueAtual = e.getQtdEstoqueAtual();
        this.qtdEstoqueMinimo = e.getQtdEstoqueMinimo();
        this.dataUltimaAtualizacao = e.getDataUltimaAtualizacao();
    }

    public Integer getIdProduto() { return idProduto; }
    public String getNome() { return nome; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public Integer getQtdEstoqueAtual() { return qtdEstoqueAtual; }
    public Integer getQtdEstoqueMinimo() { return qtdEstoqueMinimo; }
    public LocalDateTime getDataUltimaAtualizacao() { return dataUltimaAtualizacao; }
}