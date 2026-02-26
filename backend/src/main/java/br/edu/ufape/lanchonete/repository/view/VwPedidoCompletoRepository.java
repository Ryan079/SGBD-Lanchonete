package br.edu.ufape.lanchonete.repository.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.lanchonete.model.view.VwPedidoCompleto;

@Repository
public interface VwPedidoCompletoRepository extends JpaRepository<VwPedidoCompleto, Integer> {

    List<VwPedidoCompleto> findByIdPedido(Integer idPedido);

    List<VwPedidoCompleto> findBySituacao(String situacao);

    List<VwPedidoCompleto> findByCpfCliente(String cpfCliente);
}
