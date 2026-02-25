package br.edu.ufape.lanchonete.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cardapio", nullable = false)
    private Integer idCardapio; // Número identificador do item no cardápio [cite: 26]

    @Column(length = 100, nullable = false)
    private String nome; // Nome comercial do lanche ou bebida [cite: 26]

    @Column(length = 20, nullable = false)
    private String categoria; // Agrupamento lógico (ex: Lanche, Bebida, Sobremesa) [cite: 26]

    @Column(columnDefinition = "TEXT")
    private String descricao; // Detalhes dos ingredientes [cite: 26]

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal preco; // Valor de cada item do cardápio [cite: 27]

    public Cardapio() {}

    public Cardapio(String nome, String categoria, String descricao, BigDecimal preco) {
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.preco = preco;
    }

    // Getters e Setters
    public Integer getIdCardapio() { return idCardapio; }
    public void setIdCardapio(Integer idCardapio) { this.idCardapio = idCardapio; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
}