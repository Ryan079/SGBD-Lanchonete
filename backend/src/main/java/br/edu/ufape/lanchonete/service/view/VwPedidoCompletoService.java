package br.edu.ufape.lanchonete.service.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.lanchonete.dto.view.PedidoCompletoResponseDTO;
import br.edu.ufape.lanchonete.repository.view.VwPedidoCompletoRepository;

@Service
public class VwPedidoCompletoService {
    
    @Autowired
    private VwPedidoCompletoRepository repo;

    public List<PedidoCompletoResponseDTO> listarPorPedido(Integer idPedido) {
        return repo.findByIdPedido(idPedido)
                .stream()
                .map(PedidoCompletoResponseDTO::new)
                .toList();
    }

    public List<PedidoCompletoResponseDTO> listarPorSituacao(String situacao) {
        return repo.findBySituacao(situacao)
                .stream()
                .map(PedidoCompletoResponseDTO::new)
                .toList();
    }

    public List<PedidoCompletoResponseDTO> listarPorCpfCliente(String cpfCliente) {
        return repo.findByCpfCliente(cpfCliente)
                .stream()
                .map(PedidoCompletoResponseDTO::new)
                .toList();

    }
}
