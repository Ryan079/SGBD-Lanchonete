package br.edu.ufape.lanchonete.controller;

import br.edu.ufape.lanchonete.dto.CardapioRequestDTO;
import br.edu.ufape.lanchonete.dto.CardapioResponseDTO;
import br.edu.ufape.lanchonete.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<CardapioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(cardapioService.listarTodos());
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
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        cardapioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}