package br.edu.ufape.lanchonete.service.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.lanchonete.dto.view.ProdutosBaixaRotatividadeResponseDTO;
import br.edu.ufape.lanchonete.repository.view.VwProdutosBaixaRotatividadeRepository;

@Service
public class VwProdutosBaixaRotatividadeService {
    
    @Autowired
    private VwProdutosBaixaRotatividadeRepository repo;

    public List<ProdutosBaixaRotatividadeResponseDTO> listarTodos() {
        return repo.findAll()
                .stream()
                .map(ProdutosBaixaRotatividadeResponseDTO::new)
                .toList();
    }
}
