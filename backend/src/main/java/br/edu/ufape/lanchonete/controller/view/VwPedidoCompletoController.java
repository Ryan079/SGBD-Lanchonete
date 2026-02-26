package br.edu.ufape.lanchonete.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.lanchonete.dto.view.PedidoCompletoResponseDTO;
import br.edu.ufape.lanchonete.service.view.VwPedidoCompletoService;

@RestController
@RequestMapping("/api/reports/pedidos")
public class VwPedidoCompletoController {
    
    @Autowired
    private VwPedidoCompletoService service;

    @GetMapping("/{idPedido}")
    public ResponseEntity<List<PedidoCompletoResponseDTO>> porPedido(
        @PathVariable Integer idPedido) {
            return ResponseEntity.ok(service.listarPorPedido(idPedido));
        }

    @GetMapping("/situacao")
    public ResponseEntity<List<PedidoCompletoResponseDTO>> porPedido(
        @RequestParam String situacao) {
            return ResponseEntity.ok(service.listarPorSituacao(situacao));
        }
    
    @GetMapping("/cliente")
    public ResponseEntity<List<PedidoCompletoResponseDTO>> porCliente(
        @RequestParam String clienteCpf) {
            return ResponseEntity.ok(service.listarPorCpfCliente(clienteCpf));
        }
    
}
