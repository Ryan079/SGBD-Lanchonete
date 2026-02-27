package br.edu.ufape.lanchonete.controller;

import br.edu.ufape.lanchonete.dto.PedidoRequestDTO;
import br.edu.ufape.lanchonete.dto.PedidoResponseDTO;
import br.edu.ufape.lanchonete.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criar(@RequestBody PedidoRequestDTO dto) {
        return ResponseEntity.ok(pedidoService.criarPedido(dto));
    }

    @GetMapping
    public ResponseEntity<Page<PedidoResponseDTO>> listarTodos(
            @RequestParam(required = false) String situacao,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return ResponseEntity.ok(pedidoService.listarTodos(situacao, pageable));
    }

    @PatchMapping("/{id}/situacao")
    public ResponseEntity<PedidoResponseDTO> atualizarSituacao(
            @PathVariable Integer id,
            @RequestParam String situacao) {
        return ResponseEntity.ok(pedidoService.atualizarSituacao(id, situacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
