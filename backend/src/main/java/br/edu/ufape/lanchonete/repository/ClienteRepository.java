package br.edu.ufape.lanchonete.repository;

import br.edu.ufape.lanchonete.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    
    // Filtro: Busca clientes cujo nome contenha o texto digitado (ignorando maiúsculas/minúsculas)
    Page<Cliente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}