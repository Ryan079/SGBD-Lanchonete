package br.edu.ufape.lanchonete.service.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.lanchonete.dto.view.HorariosPicoResponseDTO;
import br.edu.ufape.lanchonete.repository.view.VwHorariosPicoRepository;

@Service
public class VwHorariosPicoService {
    
    @Autowired
    private VwHorariosPicoRepository repo;

    public List<HorariosPicoResponseDTO> listarTodos() {
        return repo.findAll()
                .stream()
                .map(HorariosPicoResponseDTO::new)
                .toList();
    }
}
