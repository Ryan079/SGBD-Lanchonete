package br.edu.ufape.lanchonete.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.lanchonete.model.Cardapio;
import br.edu.ufape.lanchonete.repository.CardapioRepository;

@Service
public class CardapioService {

    @Autowired
    private CardapioRepository cardapioRepository;

    public Cardapio salvar(Cardapio cardapio) {
        return cardapioRepository.save(cardapio);
    }

    public List<Cardapio> listarTodos() {
        return cardapioRepository.findAll();
    }

    public Optional<Cardapio> buscarPorId(Integer id) {
        return cardapioRepository.findById(id);
    }

    public Cardapio atualizar(Integer id, Cardapio atualizado) {
        return cardapioRepository.findById(id).map(cardapio -> {
            cardapio.setNome(atualizado.getNome());
            cardapio.setCategoria(atualizado.getCategoria());
            cardapio.setDescricao(atualizado.getDescricao());
            cardapio.setPreco(atualizado.getPreco());
            return cardapioRepository.save(cardapio);
        }).orElseThrow(() -> new RuntimeException("Item do cardápio não encontrado!"));
    }

    public void deletar(Integer id) {
        cardapioRepository.deleteById(id);
    }
}