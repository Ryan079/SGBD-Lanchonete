package br.edu.ufape.lanchonete.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public ClienteResponseDTO salvarCliente(ClienteRequestDTO dto) {
        if (clienteRepository.existsById(dto.getCpf())) {
            throw new RuntimeException("Já existe um cliente cadastrado com este CPF.");
        }

        Cliente cliente = new Cliente(dto.getCpf(), dto.getNome(), dto.getEmail(), dto.getTelefone(), dto.getEndereco());
        
        cliente = clienteRepository.save(cliente);
        
        return new ClienteResponseDTO(cliente);
    }

    public Page<ClienteResponseDTO> listarTodos(String nomeBusca, Pageable pageable) {
        Page<Cliente> paginaDeClientes;

        // se o usuario mandou um nome pra filtrar, usa o método de busca
        if (nomeBusca != null && !nomeBusca.trim().isEmpty()) {
            paginaDeClientes = clienteRepository.findByNomeContainingIgnoreCase(nomeBusca, pageable);
        } else {
            // se nao busca todos com paginação normal
            paginaDeClientes = clienteRepository.findAll(pageable);
        }

        // converte um Page<Cliente> para Page<ClienteResponseDTO>
        return paginaDeClientes.map(ClienteResponseDTO::new);
    }

    public ClienteResponseDTO buscarPorCpf(String cpf) {
        Cliente cliente = clienteRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        return new ClienteResponseDTO(cliente);
    }

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