package br.edu.ufape.lanchonete.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.lanchonete.dto.view.ClientesFrequentesResponseDTO;
import br.edu.ufape.lanchonete.service.view.VwClientesFrequentesService;

@RestController
@RequestMapping("/api/reports/clientes-frequentes")
public class VwClientesFrequentesController {
    
    @Autowired
    private VwClientesFrequentesService service;

    @GetMapping
    public ResponseEntity<List<ClientesFrequentesResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
