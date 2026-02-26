package br.edu.ufape.lanchonete.model.view;

import java.time.LocalDateTime;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade read-only mapeada para a view vw_estoque_critico.
 * Retorna apenas produtos cuja quantidade atual atingiu ou ficou
 * abaixo do mínimo configurado, expondo a quantidade a repor.
 * Nenhuma operação de escrita é permitida (@Immutable).
 */
@Entity
@Immutable
@Table(name = "vw_estoque_critico")
public class VwEstoqueCritico {

    @Id
    @Column(name = "id_produto")
    private Integer idProduto;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "unidade_medida", length = 10)
    private String unidadeMedida;

    @Column(name = "qtd_estoque_atual")
    private Integer qtdEstoqueAtual;

    @Column(name = "qtd_estoque_minimo")
    private Integer qtdEstoqueMinimo;

    @Column(name = "qtd_a_repor")
    private Integer qtdARepor;

    @Column(name = "data_ultima_atualizacao")
    private LocalDateTime dataUltimaAtualizacao;

    public VwEstoqueCritico() {}


    public Integer getIdProduto() { return idProduto; }
    public String getNome() { return nome; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public Integer getQtdEstoqueAtual() { return qtdEstoqueAtual; }
    public Integer getQtdEstoqueMinimo() { return qtdEstoqueMinimo; }
    public Integer getQtdARepor() { return qtdARepor; }
    public LocalDateTime getDataUltimaAtualizacao() { return dataUltimaAtualizacao; }
}
