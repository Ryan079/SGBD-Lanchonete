package br.edu.ufape.lanchonete.service;

import br.edu.ufape.lanchonete.model.Fornecedor;
import br.edu.ufape.lanchonete.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Fornecedor salvar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> buscarPorCnpj(String cnpj) {
        return fornecedorRepository.findById(cnpj);
    }

    public Fornecedor atualizar(String cnpj, Fornecedor atualizado) {
        return fornecedorRepository.findById(cnpj).map(fornecedor -> {
            fornecedor.setNomeEmpresa(atualizado.getNomeEmpresa());
            fornecedor.setDataCadastro(atualizado.getDataCadastro());
            fornecedor.setTelefone(atualizado.getTelefone());
            return fornecedorRepository.save(fornecedor);
        }).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado!"));
    }

    public void deletar(String cnpj) {
        fornecedorRepository.deleteById(cnpj);
    }
}