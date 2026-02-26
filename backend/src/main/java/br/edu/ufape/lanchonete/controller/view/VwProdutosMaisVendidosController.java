package br.edu.ufape.lanchonete.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.lanchonete.model.view.VwProdutosMaisVendidos;
import br.edu.ufape.lanchonete.service.view.VwProdutosMaisVendidosService;

@RestController
@RequestMapping("/api/reports/produtos-mais-vendidos")
public class VwProdutosMaisVendidosController {

    @Autowired
    private VwProdutosMaisVendidosService service;

    @GetMapping("/categoria")
    public ResponseEntity<List<VwProdutosMaisVendidos>> listarPorCategoria(
        @RequestParam String categoria) {

        return ResponseEntity.ok(service.listarPorCategoria(categoria));
    }
}
