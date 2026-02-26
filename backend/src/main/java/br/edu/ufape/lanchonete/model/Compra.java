package br.edu.ufape.lanchonete.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra", nullable = false)
    private Integer idCompra; 

    @Column(name = "data_compra", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime dataCompra; 

    @Column(name = "valor_total_compra", precision = 10, scale = 2)
    private BigDecimal valorTotalCompra = BigDecimal.ZERO; 

    // relacionamento com o Fornecedor de origem
    @ManyToOne
    @JoinColumn(name = "cnpj_fornecedor", nullable = false)
    private Fornecedor fornecedor; 

    public Compra() {}

    public Integer getIdCompra() { return idCompra; }
    public void setIdCompra(Integer idCompra) { this.idCompra = idCompra; }

    public LocalDateTime getDataCompra() { return dataCompra; }
    public void setDataCompra(LocalDateTime dataCompra) { this.dataCompra = dataCompra; }

    public BigDecimal getValorTotalCompra() { return valorTotalCompra; }
    public void setValorTotalCompra(BigDecimal valorTotalCompra) { this.valorTotalCompra = valorTotalCompra; }

    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }
}