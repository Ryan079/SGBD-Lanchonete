package br.edu.ufape.lanchonete.service.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.lanchonete.dto.view.RankingCategoriasResponseDTO;
import br.edu.ufape.lanchonete.repository.view.VwRankingCategoriasRepository;

@Service
public class VwRankingCategoriasService {
    
    @Autowired
    private VwRankingCategoriasRepository repo;

    public List<RankingCategoriasResponseDTO> listarTodos() {
        return repo.findAll()
                .stream()
                .map(RankingCategoriasResponseDTO::new)
                .toList();
    }
}
