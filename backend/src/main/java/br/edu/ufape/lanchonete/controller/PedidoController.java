package br.edu.ufape.lanchonete.controller;

import br.edu.ufape.lanchonete.dto.PedidoRequestDTO;
import br.edu.ufape.lanchonete.dto.PedidoResponseDTO;
import br.edu.ufape.lanchonete.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<PedidoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }
}