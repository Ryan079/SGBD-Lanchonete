package br.edu.ufape.lanchonete.service;

import br.edu.ufape.lanchonete.dto.ItemPedidoRequestDTO;
import br.edu.ufape.lanchonete.dto.PedidoRequestDTO;
import br.edu.ufape.lanchonete.dto.PedidoResponseDTO;
import br.edu.ufape.lanchonete.model.Cardapio;
import br.edu.ufape.lanchonete.model.Cliente;
import br.edu.ufape.lanchonete.model.ItemPedido;
import br.edu.ufape.lanchonete.model.Pedido;
import br.edu.ufape.lanchonete.repository.CardapioRepository;
import br.edu.ufape.lanchonete.repository.ClienteRepository;
import br.edu.ufape.lanchonete.repository.ItemPedidoRepository;
import br.edu.ufape.lanchonete.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CardapioRepository cardapioRepository;

    @Transactional
    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getCpfCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEnderecoEntrega(dto.getEnderecoEntrega());
        pedido.setPontoReferencia(dto.getPontoReferencia());
        pedido.setTaxaEntrega(dto.getTaxaEntrega() != null ? dto.getTaxaEntrega() : BigDecimal.ZERO);
        pedido.setTrocoPara(dto.getTrocoPara() != null ? dto.getTrocoPara() : BigDecimal.ZERO);
        pedido.setSituacao("Pendente");
        pedido.setDataHora(LocalDateTime.now());
        
        pedido = pedidoRepository.save(pedido);

        BigDecimal valorTotal = pedido.getTaxaEntrega();

        for (ItemPedidoRequestDTO itemDto : dto.getItens()) {
            Cardapio cardapio = cardapioRepository.findById(itemDto.getIdCardapio())
                    .orElseThrow(() -> new RuntimeException("Item do cardápio não encontrado: " + itemDto.getIdCardapio()));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setCardapio(cardapio);
            itemPedido.setQuantidade(itemDto.getQuantidade());
            itemPedido.setValorUnitario(cardapio.getPreco()); // Congela o preço no momento da compra

            BigDecimal subtotal = cardapio.getPreco().multiply(new BigDecimal(itemDto.getQuantidade()));
            valorTotal = valorTotal.add(subtotal);

            itemPedidoRepository.save(itemPedido);
        }

        pedido.setValorTotal(valorTotal);
        pedido = pedidoRepository.save(pedido);

        return new PedidoResponseDTO(pedido);
    }

    public Page<PedidoResponseDTO> listarTodos(String situacao, Pageable pageable) {
        Page<Pedido> pagina;
        if (situacao != null && !situacao.trim().isEmpty()) {
            pagina = pedidoRepository.findBySituacaoContainingIgnoreCase(situacao, pageable);
        } else {
            pagina = pedidoRepository.findAll(pageable);
        }
        return pagina.map(PedidoResponseDTO::new);
    }
}