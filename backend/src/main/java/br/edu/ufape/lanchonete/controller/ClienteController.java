package br.edu.ufape.lanchonete.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import br.edu.ufape.lanchonete.dto.ClienteRequestDTO;
import br.edu.ufape.lanchonete.dto.ClienteResponseDTO;
import br.edu.ufape.lanchonete.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<ClienteResponseDTO>> listarTodos(
            @RequestParam(required = false) String nome,
            @PageableDefault(size = 10, page = 0) Pageable pageable) { // Define que o padrão é a página 0 com 10 itens
        
        return ResponseEntity.ok(clienteService.listarTodos(nome, pageable));
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