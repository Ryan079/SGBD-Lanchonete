package br.edu.ufape.lanchonete.repository;

import br.edu.ufape.lanchonete.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    Page<Pedido> findBySituacaoContainingIgnoreCase(String situacao, Pageable pageable);
}