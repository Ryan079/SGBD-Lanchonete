package br.edu.ufape.lanchonete.service.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.lanchonete.dto.view.ClientesFrequentesResponseDTO;
import br.edu.ufape.lanchonete.repository.view.VwClientesFrequentesRepository;

@Service
public class VwClientesFrequentesService {
    
    @Autowired
    private VwClientesFrequentesRepository repo;

    public List<ClientesFrequentesResponseDTO> listarTodos() {
        return repo.findTopClientes()
                .stream()
                .map(ClientesFrequentesResponseDTO::new)
                .toList();
    }
}
