package br.edu.ufape.lanchonete.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto", nullable = false)
    private Integer idProduto;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(name = "unidade_medida", length = 10, nullable = false)
    private String unidadeMedida;

    @Column(name = "qtd_estoque_atual", columnDefinition = "INT DEFAULT 0")
    private Integer qtdEstoqueAtual = 0;

    @Column(name = "qtd_estoque_minimo", columnDefinition = "INT DEFAULT 5")
    private Integer qtdEstoqueMinimo = 5;

    @Column(name = "data_ultima_atualizacao", nullable = false)
    private LocalDateTime dataUltimaAtualizacao;

    public Estoque() {}

    public Estoque(String nome, String unidadeMedida, Integer qtdEstoqueAtual, Integer qtdEstoqueMinimo, LocalDateTime dataUltimaAtualizacao) {
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.qtdEstoqueAtual = qtdEstoqueAtual;
        this.qtdEstoqueMinimo = qtdEstoqueMinimo;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public Integer getIdProduto() { return idProduto; }
    public void setIdProduto(Integer idProduto) { this.idProduto = idProduto; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }

    public Integer getQtdEstoqueAtual() { return qtdEstoqueAtual; }
    public void setQtdEstoqueAtual(Integer qtdEstoqueAtual) { this.qtdEstoqueAtual = qtdEstoqueAtual; }

    public Integer getQtdEstoqueMinimo() { return qtdEstoqueMinimo; }
    public void setQtdEstoqueMinimo(Integer qtdEstoqueMinimo) { this.qtdEstoqueMinimo = qtdEstoqueMinimo; }

    public LocalDateTime getDataUltimaAtualizacao() { return dataUltimaAtualizacao; }
    public void setDataUltimaAtualizacao(LocalDateTime dataUltimaAtualizacao) { this.dataUltimaAtualizacao = dataUltimaAtualizacao; }
}