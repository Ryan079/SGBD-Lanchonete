package br.edu.ufape.lanchonete.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.lanchonete.dto.view.PedidoEmAbertoResponseDTO;
import br.edu.ufape.lanchonete.service.view.VwPedidosEmAbertoService;

@RestController
@RequestMapping("/api/reports/pedidos-em-aberto")
public class VwPedidosEmAbertoController {

    @Autowired
    private VwPedidosEmAbertoService service;

    @GetMapping("/situacao")
    public ResponseEntity<List<PedidoEmAbertoResponseDTO>> listarPorSituacao(
        @RequestParam String situacao) {
           return ResponseEntity.ok(service.listarPorSituacao(situacao)); 
        }
}
