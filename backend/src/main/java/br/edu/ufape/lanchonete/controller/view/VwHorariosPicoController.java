package br.edu.ufape.lanchonete.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.lanchonete.dto.view.HorariosPicoResponseDTO;
import br.edu.ufape.lanchonete.service.view.VwHorariosPicoService;

@RestController
@RequestMapping("/api/reports/horarios-pico")
public class VwHorariosPicoController {
    
    @Autowired
    private VwHorariosPicoService service;

    @GetMapping
    public ResponseEntity<List<HorariosPicoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
