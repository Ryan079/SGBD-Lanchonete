package br.edu.ufape.lanchonete.controller;

import br.edu.ufape.lanchonete.dto.ClienteRequestDTO;
import br.edu.ufape.lanchonete.dto.ClienteResponseDTO;
import br.edu.ufape.lanchonete.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@RequestBody ClienteRequestDTO dto) {
        try {
            return ResponseEntity.ok(clienteService.salvarCliente(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build(); // Retorna erro 400 se o CPF já existir
        }
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDTO> buscarPorCpf(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(clienteService.buscarPorCpf(cpf));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable String cpf, @RequestBody ClienteRequestDTO dto) {
        try {
            return ResponseEntity.ok(clienteService.atualizarCliente(cpf, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf) {
        try {
            clienteService.deletarCliente(cpf);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}