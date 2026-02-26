package br.edu.ufape.lanchonete.repository;

import br.edu.ufape.lanchonete.model.Cardapio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Integer> {
    Page<Cardapio> findByCategoriaContainingIgnoreCase(String categoria, Pageable pageable);
}