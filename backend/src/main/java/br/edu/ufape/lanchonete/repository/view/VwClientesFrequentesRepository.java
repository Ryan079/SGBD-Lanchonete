package br.edu.ufape.lanchonete.repository.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ufape.lanchonete.model.view.VwClientesFrequentes;

@Repository
public interface VwClientesFrequentesRepository extends JpaRepository<VwClientesFrequentes, String> {

    @Query("SELECT v FROM VwClientesFrequentes v ORDER BY v.totalPedidos DESC")
    List<VwClientesFrequentes> findTopClientes();
}
