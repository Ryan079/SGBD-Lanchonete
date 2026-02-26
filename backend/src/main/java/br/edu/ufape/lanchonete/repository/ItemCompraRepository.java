package br.edu.ufape.lanchonete.repository;

import br.edu.ufape.lanchonete.model.ItemCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompra, Integer> {}