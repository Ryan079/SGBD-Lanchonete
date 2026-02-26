package br.edu.ufape.lanchonete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.lanchonete.model.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, String> {
}