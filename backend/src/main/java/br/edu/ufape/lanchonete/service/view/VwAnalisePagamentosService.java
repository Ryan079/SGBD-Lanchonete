package br.edu.ufape.lanchonete.service.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.lanchonete.dto.view.AnalisePagamentosResponseDTO;
import br.edu.ufape.lanchonete.repository.view.VwAnalisePagamentosRepository;

@Service
public class VwAnalisePagamentosService {
    
    @Autowired
    private VwAnalisePagamentosRepository repo;

    public List<AnalisePagamentosResponseDTO> listarTodos() {
        return repo.findAll()
                .stream()
                .map(AnalisePagamentosResponseDTO::new)
                .toList();
    }
}
