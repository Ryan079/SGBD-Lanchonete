package br.edu.ufape.lanchonete.repository;

import br.edu.ufape.lanchonete.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {}