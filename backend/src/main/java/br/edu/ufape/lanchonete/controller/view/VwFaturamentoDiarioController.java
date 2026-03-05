package br.edu.ufape.lanchonete.controller.view;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.lanchonete.dto.view.FaturamentoDiarioResponseDTO;
import br.edu.ufape.lanchonete.service.view.VwFaturamentoDiarioService;

@RestController
@RequestMapping("/api/reports/faturamento-diario")
public class VwFaturamentoDiarioController {
    
    @Autowired
    private VwFaturamentoDiarioService service;

    @GetMapping
    public ResponseEntity<List<FaturamentoDiarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<FaturamentoDiarioResponseDTO>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return ResponseEntity.ok(service.listarPorPeriodo(dataInicio, dataFim));
    }
}
