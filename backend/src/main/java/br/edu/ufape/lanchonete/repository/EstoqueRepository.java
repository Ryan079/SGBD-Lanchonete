package br.edu.ufape.lanchonete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.lanchonete.model.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
}