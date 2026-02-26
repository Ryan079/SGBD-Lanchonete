package br.edu.ufape.lanchonete.repository.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.lanchonete.model.view.VwPedidosEmAberto;

@Repository
public interface VwPedidosEmAbertoRepository extends JpaRepository<VwPedidosEmAberto, Integer> {

    List<VwPedidosEmAberto> findBySituacao(String situacao);
}
