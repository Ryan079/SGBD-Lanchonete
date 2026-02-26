package br.edu.ufape.lanchonete.service;
import br.edu.ufape.lanchonete.dto.FornecedorRequestDTO;
import br.edu.ufape.lanchonete.dto.FornecedorResponseDTO;
import br.edu.ufape.lanchonete.model.Fornecedor;
import br.edu.ufape.lanchonete.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FornecedorService {
    @Autowired private FornecedorRepository repository;

    public FornecedorResponseDTO salvar(FornecedorRequestDTO dto) {
        if (repository.existsById(dto.getCnpj())) throw new RuntimeException("CNPJ já cadastrado.");
        Fornecedor f = new Fornecedor(dto.getCnpj(), dto.getNomeEmpresa(), dto.getDataCadastro(), dto.getTelefone());
        return new FornecedorResponseDTO(repository.save(f));
    }

    public List<FornecedorResponseDTO> listarTodos() {
        return repository.findAll().stream().map(FornecedorResponseDTO::new).collect(Collectors.toList());
    }

    public FornecedorResponseDTO buscarPorCnpj(String cnpj) {
        return new FornecedorResponseDTO(repository.findById(cnpj).orElseThrow(() -> new RuntimeException("Não encontrado")));
    }

    public FornecedorResponseDTO atualizar(String cnpj, FornecedorRequestDTO dto) {
        Fornecedor f = repository.findById(cnpj).orElseThrow(() -> new RuntimeException("Não encontrado"));
        f.setNomeEmpresa(dto.getNomeEmpresa());
        f.setDataCadastro(dto.getDataCadastro());
        f.setTelefone(dto.getTelefone());
        return new FornecedorResponseDTO(repository.save(f));
    }

    public void deletar(String cnpj) {
        repository.deleteById(cnpj);
    }
}