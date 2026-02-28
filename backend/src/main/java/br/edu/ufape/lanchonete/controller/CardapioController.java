package br.edu.ufape.lanchonete.controller;

import br.edu.ufape.lanchonete.dto.CardapioRequestDTO;
import br.edu.ufape.lanchonete.dto.CardapioResponseDTO;
import br.edu.ufape.lanchonete.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("/api/cardapio")
public class CardapioController {

    @Autowired
    private CardapioService cardapioService;

    @PostMapping
    public ResponseEntity<CardapioResponseDTO> criar(@RequestBody CardapioRequestDTO dto) {
        return ResponseEntity.ok(cardapioService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<Page<CardapioResponseDTO>> listarTodos(
            @RequestParam(required = false) String categoria,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        
        return ResponseEntity.ok(cardapioService.listarTodos(categoria, pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CardapioResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(cardapioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardapioResponseDTO> atualizar(@PathVariable Integer id, @RequestBody CardapioRequestDTO dto) {
        return ResponseEntity.ok(cardapioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        try {
            cardapioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(409).body("Item não pode ser excluído pois está vinculado a pedidos existentes.");
        }
    }
}