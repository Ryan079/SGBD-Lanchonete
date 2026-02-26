package br.edu.ufape.lanchonete.service;

import br.edu.ufape.lanchonete.dto.CardapioRequestDTO;
import br.edu.ufape.lanchonete.dto.CardapioResponseDTO;
import br.edu.ufape.lanchonete.model.Cardapio;
import br.edu.ufape.lanchonete.repository.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class CardapioService {
    @Autowired private CardapioRepository repository;

    public CardapioResponseDTO salvar(CardapioRequestDTO dto) {
        Cardapio c = new Cardapio(dto.getNome(), dto.getCategoria(), dto.getDescricao(), dto.getPreco());
        return new CardapioResponseDTO(repository.save(c));
    }

    public Page<CardapioResponseDTO> listarTodos(String categoriaBusca, Pageable pageable) {
        Page<Cardapio> pagina;
        if (categoriaBusca != null && !categoriaBusca.trim().isEmpty()) {
            pagina = cardapioRepository.findByCategoriaContainingIgnoreCase(categoriaBusca, pageable);
        } else {
            pagina = cardapioRepository.findAll(pageable);
        }
        return pagina.map(CardapioResponseDTO::new);
    }

    public CardapioResponseDTO buscarPorId(Integer id) {
        return new CardapioResponseDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado")));
    }

    public CardapioResponseDTO atualizar(Integer id, CardapioRequestDTO dto) {
        Cardapio c = repository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));
        c.setNome(dto.getNome());
        c.setCategoria(dto.getCategoria());
        c.setDescricao(dto.getDescricao());
        c.setPreco(dto.getPreco());
        return new CardapioResponseDTO(repository.save(c));
    }

    public void deletar(Integer id) { repository.deleteById(id); }
}