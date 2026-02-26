package br.edu.ufape.lanchonete.service.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.lanchonete.model.view.VwEstoqueCritico;
import br.edu.ufape.lanchonete.repository.view.VwEstoqueCriticoRepository;

@Service
public class VwEstoqueCriticoService {
    
    @Autowired
    private VwEstoqueCriticoRepository repo;
    
    public List<VwEstoqueCritico> listarEstoqueCritico() {
        return repo.findAll();
    }
}
