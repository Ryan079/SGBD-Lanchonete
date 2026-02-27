package br.edu.ufape.lanchonete.repository;

import br.edu.ufape.lanchonete.model.ItemPedido;
import br.edu.ufape.lanchonete.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
    void deleteAllByPedido(Pedido pedido);
}
