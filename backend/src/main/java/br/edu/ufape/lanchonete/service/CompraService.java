package br.edu.ufape.lanchonete.service;

import br.edu.ufape.lanchonete.dto.CompraRequestDTO;
import br.edu.ufape.lanchonete.dto.CompraResponseDTO;
import br.edu.ufape.lanchonete.dto.ItemCompraRequestDTO;
import br.edu.ufape.lanchonete.model.Compra;
import br.edu.ufape.lanchonete.model.Estoque;
import br.edu.ufape.lanchonete.model.Fornecedor;
import br.edu.ufape.lanchonete.model.ItemCompra;
import br.edu.ufape.lanchonete.repository.CompraRepository;
import br.edu.ufape.lanchonete.repository.EstoqueRepository;
import br.edu.ufape.lanchonete.repository.FornecedorRepository;
import br.edu.ufape.lanchonete.repository.ItemCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraService {

    @Autowired private CompraRepository compraRepository;
    @Autowired private ItemCompraRepository itemCompraRepository;
    @Autowired private FornecedorRepository fornecedorRepository;
    @Autowired private EstoqueRepository estoqueRepository;

    @Transactional
    public CompraResponseDTO registrarCompra(CompraRequestDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(dto.getCnpjFornecedor())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado."));

        Compra compra = new Compra();
        compra.setFornecedor(fornecedor);
        compra.setDataCompra(LocalDateTime.now());
        compra = compraRepository.save(compra);

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemCompraRequestDTO itemDto : dto.getItens()) {
            Estoque produto = estoqueRepository.findById(itemDto.getIdProduto())
                    .orElseThrow(() -> new RuntimeException("Produto de estoque não encontrado."));

            ItemCompra itemCompra = new ItemCompra();
            itemCompra.setCompra(compra);
            itemCompra.setProduto(produto);
            itemCompra.setQuantidade(itemDto.getQuantidade());
            itemCompra.setValorUnitario(itemDto.getValorUnitario());
            itemCompra.setLote(itemDto.getLote());

            BigDecimal subtotal = itemDto.getValorUnitario().multiply(new BigDecimal(itemDto.getQuantidade()));
            valorTotal = valorTotal.add(subtotal);

            produto.setQtdEstoqueAtual(produto.getQtdEstoqueAtual() + itemDto.getQuantidade());
            produto.setDataUltimaAtualizacao(LocalDateTime.now());
            estoqueRepository.save(produto);

            itemCompraRepository.save(itemCompra);
        }

        compra.setValorTotalCompra(valorTotal);
        return new CompraResponseDTO(compraRepository.save(compra));
    }

    public List<CompraResponseDTO> listarTodos() {
        return compraRepository.findAll().stream().map(CompraResponseDTO::new).collect(Collectors.toList());
    }
}