package br.edu.ufape.lanchonete.service.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.lanchonete.model.view.VwProdutosMaisVendidos;
import br.edu.ufape.lanchonete.repository.view.VwProdutosMaisVendidosRepository;

@Service
public class VwProdutosMaisVendidosService {

    @Autowired
    private VwProdutosMaisVendidosRepository repo;

    public List<VwProdutosMaisVendidos> listarPorCategoria(String categoria) {
        return repo.findByCategoria(categoria);
    }
    
}
