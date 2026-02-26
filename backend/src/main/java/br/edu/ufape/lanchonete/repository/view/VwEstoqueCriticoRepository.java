package br.edu.ufape.lanchonete.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.lanchonete.model.view.VwEstoqueCritico;

@Repository
public interface VwEstoqueCriticoRepository extends JpaRepository<VwEstoqueCritico, Integer> {
}
