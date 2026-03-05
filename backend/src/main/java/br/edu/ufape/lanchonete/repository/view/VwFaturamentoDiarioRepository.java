package br.edu.ufape.lanchonete.repository.view;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ufape.lanchonete.model.view.VwFaturamentoDiario;

@Repository
public interface VwFaturamentoDiarioRepository extends JpaRepository<VwFaturamentoDiario, LocalDate> {

    @Query("SELECT v FROM VwFaturamentoDiario v WHERE v.data BETWEEN :dataInicio AND :dataFim ORDER BY v.data DESC")
    List<VwFaturamentoDiario> findByPeriodo(LocalDate dataInicio, LocalDate dataFim);
}
