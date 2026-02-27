package br.edu.ufape.lanchonete.repository;

import br.edu.ufape.lanchonete.model.Pagamento;
import br.edu.ufape.lanchonete.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    void deleteAllByPedido(Pedido pedido);
}
