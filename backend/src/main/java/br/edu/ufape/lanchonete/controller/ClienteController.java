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
        return ResponseEntity.ok(clienteService.salvarCliente(dto));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDTO> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(clienteService.buscarPorCpf(cpf));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable String cpf, @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.ok(clienteService.atualizarCliente(cpf, dto));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf) {
        clienteService.deletarCliente(cpf);
        return ResponseEntity.noContent().build();
    }
}