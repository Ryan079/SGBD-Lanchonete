package br.edu.ufape.lanchonete.service;

import br.edu.ufape.lanchonete.dto.ClienteRequestDTO;
import br.edu.ufape.lanchonete.dto.ClienteResponseDTO;
import br.edu.ufape.lanchonete.model.Cliente;
import br.edu.ufape.lanchonete.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // CREATE
    public ClienteResponseDTO salvarCliente(ClienteRequestDTO dto) {
        if (clienteRepository.existsById(dto.getCpf())) {
            throw new RuntimeException("Já existe um cliente cadastrado com este CPF.");
        }

        Cliente cliente = new Cliente(dto.getCpf(), dto.getNome(), dto.getEmail(), dto.getTelefone(), dto.getEndereco());
        
        cliente = clienteRepository.save(cliente);
        
        return new ClienteResponseDTO(cliente);
    }

    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(ClienteResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO buscarPorCpf(String cpf) {
        Cliente cliente = clienteRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        return new ClienteResponseDTO(cliente);
    }

    // UPDATE
    public ClienteResponseDTO atualizarCliente(String cpf, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());

        cliente = clienteRepository.save(cliente);
        return new ClienteResponseDTO(cliente);
    }

    public void deletarCliente(String cpf) {
        if (!clienteRepository.existsById(cpf)) {
            throw new RuntimeException("Cliente não encontrado para deleção.");
        }
        clienteRepository.deleteById(cpf);
    }
}