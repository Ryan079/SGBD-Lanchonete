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
        return ResponseEntity.ok(funcionarioService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(funcionarioService.listarTodos());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(funcionarioService.buscarPorCpf(cpf));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<FuncionarioResponseDTO> atualizar(@PathVariable String cpf, @RequestBody FuncionarioRequestDTO dto) {
        return ResponseEntity.ok(funcionarioService.atualizar(cpf, dto));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf) {
        funcionarioService.deletar(cpf);
        return ResponseEntity.noContent().build();
    }
}