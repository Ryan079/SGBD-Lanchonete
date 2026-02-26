package br.edu.ufape.lanchonete.service;

import br.edu.ufape.lanchonete.dto.FuncionarioRequestDTO;
import br.edu.ufape.lanchonete.dto.FuncionarioResponseDTO;
import br.edu.ufape.lanchonete.model.Funcionario;
import br.edu.ufape.lanchonete.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioResponseDTO salvar(FuncionarioRequestDTO dto) {
        if (funcionarioRepository.existsById(dto.getCpf())) {
            throw new RuntimeException("Já existe um funcionário cadastrado com este CPF.");
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setCpf(dto.getCpf());
        funcionario.setNome(dto.getNome());
        funcionario.setCargo(dto.getCargo());
        funcionario.setDataAdmissao(dto.getDataAdmissao());
        funcionario.setSalario(dto.getSalario());

        if (dto.getCpfGerente() != null && !dto.getCpfGerente().isEmpty()) {
            Funcionario gerente = funcionarioRepository.findById(dto.getCpfGerente())
                    .orElseThrow(() -> new RuntimeException("Gerente não encontrado!"));
            funcionario.setGerente(gerente);
        }

        funcionario = funcionarioRepository.save(funcionario);
        return new FuncionarioResponseDTO(funcionario);
    }

    public List<FuncionarioResponseDTO> listarTodos() {
        return funcionarioRepository.findAll().stream()
                .map(FuncionarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    public FuncionarioResponseDTO buscarPorCpf(String cpf) {
        Funcionario funcionario = funcionarioRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));
        return new FuncionarioResponseDTO(funcionario);
    }

    public FuncionarioResponseDTO atualizar(String cpf, FuncionarioRequestDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));

        funcionario.setNome(dto.getNome());
        funcionario.setCargo(dto.getCargo());
        funcionario.setDataAdmissao(dto.getDataAdmissao());
        funcionario.setSalario(dto.getSalario());

        if (dto.getCpfGerente() != null && !dto.getCpfGerente().isEmpty()) {
            Funcionario gerente = funcionarioRepository.findById(dto.getCpfGerente())
                    .orElseThrow(() -> new RuntimeException("Gerente não encontrado!"));
            funcionario.setGerente(gerente);
        } else {
            funcionario.setGerente(null);
        }

        funcionario = funcionarioRepository.save(funcionario);
        return new FuncionarioResponseDTO(funcionario);
    }

    public void deletar(String cpf) {
        if (!funcionarioRepository.existsById(cpf)) {
            throw new RuntimeException("Funcionário não encontrado para deleção.");
        }
        funcionarioRepository.deleteById(cpf);
    }
}