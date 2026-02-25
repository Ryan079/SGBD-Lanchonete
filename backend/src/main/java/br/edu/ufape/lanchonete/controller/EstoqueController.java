package br.edu.ufape.lanchonete.controller;

import br.edu.ufape.lanchonete.dto.EstoqueRequestDTO;
import br.edu.ufape.lanchonete.dto.EstoqueResponseDTO;
import br.edu.ufape.lanchonete.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<EstoqueResponseDTO>> listarTodos() {
        return ResponseEntity.ok(estoqueService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(estoqueService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> atualizar(@PathVariable Integer id, @RequestBody EstoqueRequestDTO dto) {
        return ResponseEntity.ok(estoqueService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        estoqueService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}