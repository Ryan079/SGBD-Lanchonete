package br.edu.ufape.lanchonete.service.view;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.lanchonete.dto.view.FaturamentoDiarioResponseDTO;
import br.edu.ufape.lanchonete.repository.view.VwFaturamentoDiarioRepository;

@Service
public class VwFaturamentoDiarioService {
    
    @Autowired
    private VwFaturamentoDiarioRepository repo;

    public List<FaturamentoDiarioResponseDTO> listarTodos() {
        return repo.findAll()
                .stream()
                .map(FaturamentoDiarioResponseDTO::new)
                .toList();
    }

    public List<FaturamentoDiarioResponseDTO> listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return repo.findByPeriodo(dataInicio, dataFim)
                .stream()
                .map(FaturamentoDiarioResponseDTO::new)
                .toList();
    }
}
