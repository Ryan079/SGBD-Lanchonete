package br.edu.ufape.lanchonete.repository.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.lanchonete.model.view.VwProdutosMaisVendidos;

@Repository
public interface VwProdutosMaisVendidosRepository extends JpaRepository<VwProdutosMaisVendidos, Integer> {

    List<VwProdutosMaisVendidos> findByCategoria(String categoria);
}
