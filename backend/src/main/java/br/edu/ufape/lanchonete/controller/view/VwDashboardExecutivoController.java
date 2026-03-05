package br.edu.ufape.lanchonete.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.lanchonete.dto.view.DashboardExecutivoResponseDTO;
import br.edu.ufape.lanchonete.service.view.VwDashboardExecutivoService;

@RestController
@RequestMapping("/api/reports/dashboard-executivo")
public class VwDashboardExecutivoController {
    
    @Autowired
    private VwDashboardExecutivoService service;

    @GetMapping
    public ResponseEntity<DashboardExecutivoResponseDTO> obterDashboard() {
        return service.obterDashboard()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
