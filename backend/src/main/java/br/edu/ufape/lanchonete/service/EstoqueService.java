package br.edu.ufape.lanchonete.service;
import br.edu.ufape.lanchonete.dto.EstoqueRequestDTO;
import br.edu.ufape.lanchonete.dto.EstoqueResponseDTO;
import br.edu.ufape.lanchonete.model.Estoque;
import br.edu.ufape.lanchonete.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueService {
    @Autowired private EstoqueRepository repository;

    public EstoqueResponseDTO salvar(EstoqueRequestDTO dto) {
        Estoque e = new Estoque(dto.getNome(), dto.getUnidadeMedida(), dto.getQtdEstoqueAtual(), dto.getQtdEstoqueMinimo(), LocalDateTime.now());
        return new EstoqueResponseDTO(repository.save(e));
    }

    public List<EstoqueResponseDTO> listarTodos() {
        return repository.findAll().stream().map(EstoqueResponseDTO::new).collect(Collectors.toList());
    }

    public EstoqueResponseDTO buscarPorId(Integer id) {
        return new EstoqueResponseDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado")));
    }

    public EstoqueResponseDTO atualizar(Integer id, EstoqueRequestDTO dto) {
        Estoque e = repository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));
        e.setNome(dto.getNome());
        e.setUnidadeMedida(dto.getUnidadeMedida());
        e.setQtdEstoqueAtual(dto.getQtdEstoqueAtual());
        e.setQtdEstoqueMinimo(dto.getQtdEstoqueMinimo());
        e.setDataUltimaAtualizacao(LocalDateTime.now());
        return new EstoqueResponseDTO(repository.save(e));
    }

    public void deletar(Integer id) { repository.deleteById(id); }
}