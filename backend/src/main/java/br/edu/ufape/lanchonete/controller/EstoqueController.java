package br.edu.ufape.lanchonete.controller;

import br.edu.ufape.lanchonete.dto.EstoqueRequestDTO;
import br.edu.ufape.lanchonete.dto.EstoqueResponseDTO;
import br.edu.ufape.lanchonete.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PostMapping
    public ResponseEntity<EstoqueResponseDTO> criar(@RequestBody EstoqueRequestDTO dto) {
        return ResponseEntity.ok(estoqueService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<Page<EstoqueResponseDTO>> listarTodos(
            @RequestParam(required = false) String nome,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        
        return ResponseEntity.ok(estoqueService.listarTodos(nome, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(estoqueService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> atualizar(@PathVariable Integer id, @RequestBody EstoqueRequestDTO dto) {
        try {
            return ResponseEntity.ok(estoqueService.atualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            estoqueService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}