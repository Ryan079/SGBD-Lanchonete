package br.edu.ufape.lanchonete.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.lanchonete.dto.view.RankingCategoriasResponseDTO;
import br.edu.ufape.lanchonete.service.view.VwRankingCategoriasService;

@RestController
@RequestMapping("/api/reports/ranking-categorias")
public class VwRankingCategoriasController {
    
    @Autowired
    private VwRankingCategoriasService service;

    @GetMapping
    public ResponseEntity<List<RankingCategoriasResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
