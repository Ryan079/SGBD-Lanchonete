package br.edu.ufape.lanchonete.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.lanchonete.model.Estoque;
import br.edu.ufape.lanchonete.service.EstoqueService;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PostMapping
    public ResponseEntity<Estoque> criar(@RequestBody Estoque estoque) {
        return ResponseEntity.ok(estoqueService.salvar(estoque));
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> listarTodos() {
        return ResponseEntity.ok(estoqueService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> buscarPorId(@PathVariable Integer id) {
        return estoqueService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> atualizar(@PathVariable Integer id, @RequestBody Estoque estoque) {
        try {
            return ResponseEntity.ok(estoqueService.atualizar(id, estoque));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        estoqueService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}