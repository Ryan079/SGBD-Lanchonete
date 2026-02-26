package br.edu.ufape.lanchonete.model.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade read-only mapeada para a view vw_pedidos_em_aberto.
 * Retorna apenas pedidos com situação 'Pendente' ou 'Em Preparo',
 * ordenados do mais antigo para o mais novo (fila FIFO da cozinha).
 * Nenhuma operação de escrita é permitida (@Immutable).
 */
@Entity
@Immutable
@Table(name = "vw_pedidos_em_aberto")
public class VwPedidosEmAberto {

    @Id
    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "situacao", length = 20)
    private String situacao;

    @Column(name = "endereco_entrega", columnDefinition = "TEXT")
    private String enderecoEntrega;

    @Column(name = "ponto_referencia", columnDefinition = "TEXT")
    private String pontoReferencia;

    @Column(name = "taxa_entrega", precision = 10, scale = 2)
    private BigDecimal taxaEntrega;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "nome_cliente", length = 100)
    private String nomeCliente;

    @Column(name = "telefone_cliente", length = 20)
    private String telefoneCliente;

    public VwPedidosEmAberto() {}


    public Integer getIdPedido() { return idPedido; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getSituacao() { return situacao; }
    public String getEnderecoEntrega() { return enderecoEntrega; }
    public String getPontoReferencia() { return pontoReferencia; }
    public BigDecimal getTaxaEntrega() { return taxaEntrega; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public String getNomeCliente() { return nomeCliente; }
    public String getTelefoneCliente() { return telefoneCliente; }
}
