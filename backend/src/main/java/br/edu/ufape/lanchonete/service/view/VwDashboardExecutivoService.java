package br.edu.ufape.lanchonete.service.view;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.lanchonete.dto.view.DashboardExecutivoResponseDTO;
import br.edu.ufape.lanchonete.model.view.VwDashboardExecutivo;
import br.edu.ufape.lanchonete.repository.view.VwDashboardExecutivoRepository;

@Service
public class VwDashboardExecutivoService {
    
    @Autowired
    private VwDashboardExecutivoRepository repo;

    public Optional<DashboardExecutivoResponseDTO> obterDashboard() {
        List<VwDashboardExecutivo> result = repo.findAll();
        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new DashboardExecutivoResponseDTO(result.get(0)));
    }
}
