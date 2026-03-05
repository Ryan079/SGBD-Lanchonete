package br.edu.ufape.lanchonete.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.lanchonete.dto.view.ProdutosBaixaRotatividadeResponseDTO;
import br.edu.ufape.lanchonete.service.view.VwProdutosBaixaRotatividadeService;

@RestController
@RequestMapping("/api/reports/produtos-baixa-rotatividade")
public class VwProdutosBaixaRotatividadeController {
    
    @Autowired
    private VwProdutosBaixaRotatividadeService service;

    @GetMapping
    public ResponseEntity<List<ProdutosBaixaRotatividadeResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
