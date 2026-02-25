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
        return ResponseEntity.ok(fornecedorService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<FornecedorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(fornecedorService.listarTodos());
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<FornecedorResponseDTO> buscarPorCnpj(@PathVariable String cnpj) {
        return ResponseEntity.ok(fornecedorService.buscarPorCnpj(cnpj));
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<FornecedorResponseDTO> atualizar(@PathVariable String cnpj, @RequestBody FornecedorRequestDTO dto) {
        return ResponseEntity.ok(fornecedorService.atualizar(cnpj, dto));
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> deletar(@PathVariable String cnpj) {
        fornecedorService.deletar(cnpj);
        return ResponseEntity.noContent().build();
    }
}