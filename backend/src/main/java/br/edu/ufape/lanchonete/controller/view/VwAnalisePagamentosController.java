package br.edu.ufape.lanchonete.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.lanchonete.dto.view.AnalisePagamentosResponseDTO;
import br.edu.ufape.lanchonete.service.view.VwAnalisePagamentosService;

@RestController
@RequestMapping("/api/reports/analise-pagamentos")
public class VwAnalisePagamentosController {
    
    @Autowired
    private VwAnalisePagamentosService service;

    @GetMapping
    public ResponseEntity<List<AnalisePagamentosResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
