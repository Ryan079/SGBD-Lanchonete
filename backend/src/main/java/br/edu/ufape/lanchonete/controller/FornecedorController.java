package br.edu.ufape.lanchonete.controller;

import br.edu.ufape.lanchonete.dto.FornecedorRequestDTO;
import br.edu.ufape.lanchonete.dto.FornecedorResponseDTO;
import br.edu.ufape.lanchonete.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<FornecedorResponseDTO> criar(@RequestBody FornecedorRequestDTO dto) {
        try {
            return ResponseEntity.ok(fornecedorService.salvar(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FornecedorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(fornecedorService.listarTodos());
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<FornecedorResponseDTO> buscarPorCnpj(@PathVariable String cnpj) {
        try {
            return ResponseEntity.ok(fornecedorService.buscarPorCnpj(cnpj));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<FornecedorResponseDTO> atualizar(@PathVariable String cnpj, @RequestBody FornecedorRequestDTO dto) {
        try {
            return ResponseEntity.ok(fornecedorService.atualizar(cnpj, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> deletar(@PathVariable String cnpj) {
        try {
            fornecedorService.deletar(cnpj);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}