package br.edu.ufape.lanchonete.service;

import br.edu.ufape.lanchonete.dto.PagamentoRequestDTO;
import br.edu.ufape.lanchonete.dto.PagamentoResponseDTO;
import br.edu.ufape.lanchonete.model.Pagamento;
import br.edu.ufape.lanchonete.model.Pedido;
import br.edu.ufape.lanchonete.repository.PagamentoRepository;
import br.edu.ufape.lanchonete.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired private PagamentoRepository pagamentoRepository;
    @Autowired private PedidoRepository pedidoRepository;

    public PagamentoResponseDTO registrarPagamento(PagamentoRequestDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.getIdPedido())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(pedido);
        pagamento.setValorPago(dto.getValorPago());
        pagamento.setMetodoPagamento(dto.getMetodoPagamento());
        pagamento.setDatahoraPagamento(LocalDateTime.now());

        pedido.setSituacao("Pago");
        pedidoRepository.save(pedido);

        return new PagamentoResponseDTO(pagamentoRepository.save(pagamento));
    }

    public List<PagamentoResponseDTO> listarTodos() {
        return pagamentoRepository.findAll().stream().map(PagamentoResponseDTO::new).collect(Collectors.toList());
    }
}