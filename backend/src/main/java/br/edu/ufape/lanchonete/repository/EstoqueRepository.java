package br.edu.ufape.lanchonete.repository;

import br.edu.ufape.lanchonete.model.Estoque;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
    Page<Estoque> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}