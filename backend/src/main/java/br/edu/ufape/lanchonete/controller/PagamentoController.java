package br.edu.ufape.lanchonete.controller;

import br.edu.ufape.lanchonete.dto.PagamentoRequestDTO;
import br.edu.ufape.lanchonete.dto.PagamentoResponseDTO;
import br.edu.ufape.lanchonete.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> registrar(@RequestBody PagamentoRequestDTO dto) {
        return ResponseEntity.ok(pagamentoService.registrarPagamento(dto));
    }

    @GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pagamentoService.listarTodos());
    }
}