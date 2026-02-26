package br.edu.ufape.lanchonete.controller;

import br.edu.ufape.lanchonete.dto.CompraRequestDTO;
import br.edu.ufape.lanchonete.dto.CompraResponseDTO;
import br.edu.ufape.lanchonete.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<CompraResponseDTO> registrar(@RequestBody CompraRequestDTO dto) {
        return ResponseEntity.ok(compraService.registrarCompra(dto));
    }

    @GetMapping
    public ResponseEntity<List<CompraResponseDTO>> listarTodos() {
        return ResponseEntity.ok(compraService.listarTodos());
    }
}