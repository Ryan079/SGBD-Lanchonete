package br.edu.ufape.lanchonete.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.lanchonete.model.view.VwEstoqueCritico;
import br.edu.ufape.lanchonete.service.view.VwEstoqueCriticoService;

@RestController
@RequestMapping("/api/reports/estoque")
public class VwEstoqueCriticoController {
    
    @Autowired
    private VwEstoqueCriticoService service;

    @GetMapping("/critico")
    public ResponseEntity<List<VwEstoqueCritico>> listarEstoqueCritico() {
        return ResponseEntity.ok(service.listarEstoqueCritico());
    }
    
}
