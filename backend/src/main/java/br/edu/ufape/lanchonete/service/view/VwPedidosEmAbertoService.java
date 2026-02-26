package br.edu.ufape.lanchonete.service.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.lanchonete.dto.view.PedidoEmAbertoResponseDTO;
import br.edu.ufape.lanchonete.repository.view.VwPedidosEmAbertoRepository;

@Service
public class VwPedidosEmAbertoService {

    @Autowired
    private VwPedidosEmAbertoRepository repo;

    public List<PedidoEmAbertoResponseDTO> listarPorSituacao(String situacao) {
        return repo.findBySituacao(situacao)
                .stream()
                .map(PedidoEmAbertoResponseDTO::new)
                .toList();
    }
}
