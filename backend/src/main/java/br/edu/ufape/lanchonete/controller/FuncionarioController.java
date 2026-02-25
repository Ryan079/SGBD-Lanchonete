package br.edu.ufape.lanchonete.controller;

import br.edu.ufape.lanchonete.dto.FuncionarioRequestDTO;
import br.edu.ufape.lanchonete.dto.FuncionarioResponseDTO;
import br.edu.ufape.lanchonete.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> criar(@RequestBody FuncionarioRequestDTO dto) {
        try {
            return ResponseEntity.ok(funcionarioService.salvar(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(funcionarioService.listarTodos());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorCpf(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(funcionarioService.buscarPorCpf(cpf));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<FuncionarioResponseDTO> atualizar(@PathVariable String cpf, @RequestBody FuncionarioRequestDTO dto) {
        try {
            return ResponseEntity.ok(funcionarioService.atualizar(cpf, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf) {
        try {
            funcionarioService.deletar(cpf);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}