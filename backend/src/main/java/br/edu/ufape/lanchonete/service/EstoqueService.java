package br.edu.ufape.lanchonete.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.lanchonete.model.Estoque;
import br.edu.ufape.lanchonete.repository.EstoqueRepository;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public Estoque salvar(Estoque estoque) {
        estoque.setDataUltimaAtualizacao(LocalDateTime.now());
        return estoqueRepository.save(estoque);
    }

    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }

    public Optional<Estoque> buscarPorId(Integer id) {
        return estoqueRepository.findById(id);
    }

    public Estoque atualizar(Integer id, Estoque atualizado) {
        return estoqueRepository.findById(id).map(estoque -> {
            estoque.setNome(atualizado.getNome());
            estoque.setUnidadeMedida(atualizado.getUnidadeMedida());
            estoque.setQtdEstoqueAtual(atualizado.getQtdEstoqueAtual());
            estoque.setQtdEstoqueMinimo(atualizado.getQtdEstoqueMinimo());
            estoque.setDataUltimaAtualizacao(LocalDateTime.now());
            return estoqueRepository.save(estoque);
        }).orElseThrow(() -> new RuntimeException("Produto de estoque não encontrado!"));
    }

    public void deletar(Integer id) {
        estoqueRepository.deleteById(id);
    }
}