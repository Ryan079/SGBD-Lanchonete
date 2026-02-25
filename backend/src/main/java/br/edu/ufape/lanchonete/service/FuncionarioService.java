package br.edu.ufape.lanchonete.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.lanchonete.model.Funcionario;
import br.edu.ufape.lanchonete.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario salvar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorCpf(String cpf) {
        return funcionarioRepository.findById(cpf);
    }

    public Funcionario atualizar(String cpf, Funcionario atualizado) {
        return funcionarioRepository.findById(cpf).map(funcionario -> {
            funcionario.setNome(atualizado.getNome());
            funcionario.setCargo(atualizado.getCargo());
            funcionario.setDataAdmissao(atualizado.getDataAdmissao());
            funcionario.setSalario(atualizado.getSalario());
            
            // Verifica se um gerente foi enviado na atualização
            if (atualizado.getGerente() != null && atualizado.getGerente().getCpf() != null) {
                Funcionario gerente = funcionarioRepository.findById(atualizado.getGerente().getCpf()).orElse(null);
                funcionario.setGerente(gerente);
            } else {
                funcionario.setGerente(null);
            }
            
            return funcionarioRepository.save(funcionario);
        }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));
    }

    public void deletar(String cpf) {
        funcionarioRepository.deleteById(cpf);
    }
}