package br.edu.ufape.lanchonete.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.lanchonete.dto.view.AnalisePagamentosResponseDTO;
import br.edu.ufape.lanchonete.dto.view.ClientesFrequentesResponseDTO;
import br.edu.ufape.lanchonete.dto.view.FaturamentoDiarioResponseDTO;
import br.edu.ufape.lanchonete.dto.view.HorariosPicoResponseDTO;
import br.edu.ufape.lanchonete.dto.view.ProdutosBaixaRotatividadeResponseDTO;
import br.edu.ufape.lanchonete.dto.view.RankingCategoriasResponseDTO;
import br.edu.ufape.lanchonete.service.ExportService;
import br.edu.ufape.lanchonete.service.view.VwAnalisePagamentosService;
import br.edu.ufape.lanchonete.service.view.VwClientesFrequentesService;
import br.edu.ufape.lanchonete.service.view.VwFaturamentoDiarioService;
import br.edu.ufape.lanchonete.service.view.VwHorariosPicoService;
import br.edu.ufape.lanchonete.service.view.VwProdutosBaixaRotatividadeService;
import br.edu.ufape.lanchonete.service.view.VwRankingCategoriasService;

/**
 * Controller consolidado para exportação de relatórios em diferentes formatos.
 */
@RestController
@RequestMapping("/api/reports/export")
public class ReportsExportController {

    @Autowired private ExportService exportService;
    @Autowired private VwFaturamentoDiarioService faturamentoDiarioService;
    @Autowired private VwClientesFrequentesService clientesFrequentesService;
    @Autowired private VwHorariosPicoService horariosPicoService;
    @Autowired private VwAnalisePagamentosService analisePagamentosService;
    @Autowired private VwProdutosBaixaRotatividadeService produtosBaixaRotatividadeService;
    @Autowired private VwRankingCategoriasService rankingCategoriasService;

    // ========== FATURAMENTO DIÁRIO ==========
    
    @GetMapping("/faturamento-diario/pdf")
    public ResponseEntity<byte[]> exportarFaturamentoDiarioPDF(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        try {
            List<FaturamentoDiarioResponseDTO> dados = (dataInicio != null && dataFim != null) 
                ? faturamentoDiarioService.listarPorPeriodo(dataInicio, dataFim)
                : faturamentoDiarioService.listarTodos();
            
            String[] colunas = {"Data", "Total Pedidos", "Faturamento Total", "Ticket Médio", "Ticket Mínimo", "Ticket Máximo"};
            String[] metodos = {"getData", "getTotalPedidos", "getFaturamentoTotal", "getTicketMedio", "getTicketMinimo", "getTicketMaximo"};
            
            byte[] pdf = exportService.exportarPDF(dados, "Relatório de Faturamento Diário", colunas, metodos);
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=faturamento_diario.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/faturamento-diario/csv")
    public ResponseEntity<byte[]> exportarFaturamentoDiarioCSV(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        try {
            List<FaturamentoDiarioResponseDTO> dados = (dataInicio != null && dataFim != null) 
                ? faturamentoDiarioService.listarPorPeriodo(dataInicio, dataFim)
                : faturamentoDiarioService.listarTodos();
            
            String[] colunas = {"Data", "Total Pedidos", "Faturamento Total", "Ticket Médio", "Ticket Mínimo", "Ticket Máximo"};
            String[] metodos = {"getData", "getTotalPedidos", "getFaturamentoTotal", "getTicketMedio", "getTicketMinimo", "getTicketMaximo"};
            
            byte[] csv = exportService.exportarCSV(dados, colunas, metodos);
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=faturamento_diario.csv")
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(csv);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== CLIENTES FREQUENTES ==========
    
    @GetMapping("/clientes-frequentes/pdf")
    public ResponseEntity<byte[]> exportarClientesFrequentesPDF() {
        try {
            List<ClientesFrequentesResponseDTO> dados = clientesFrequentesService.listarTodos();
            
            String[] colunas = {"CPF", "Cliente", "Telefone", "Total Pedidos", "Valor Total Gasto", "Ticket Médio"};
            String[] metodos = {"getCpf", "getCliente", "getTelefone", "getTotalPedidos", "getValorTotalGasto", "getTicketMedio"};
            
            byte[] pdf = exportService.exportarPDF(dados, "Relatório de Clientes Frequentes", colunas, metodos);
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clientes_frequentes.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/clientes-frequentes/csv")
    public ResponseEntity<byte[]> exportarClientesFrequentesCSV() {
        try {
            List<ClientesFrequentesResponseDTO> dados = clientesFrequentesService.listarTodos();
            
            String[] colunas = {"CPF", "Cliente", "Telefone", "Email", "Total Pedidos", "Valor Total Gasto", "Ticket Médio"};
            String[] metodos = {"getCpf", "getCliente", "getTelefone", "getEmail", "getTotalPedidos", "getValorTotalGasto", "getTicketMedio"};
            
            byte[] csv = exportService.exportarCSV(dados, colunas, metodos);
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clientes_frequentes.csv")
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(csv);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== HORÁRIOS DE PICO ==========
    
    @GetMapping("/horarios-pico/pdf")
    public ResponseEntity<byte[]> exportarHorariosPicoPDF() {
        try {
            List<HorariosPicoResponseDTO> dados = horariosPicoService.listarTodos();
            
            String[] colunas = {"Hora", "Quantidade Pedidos", "Ticket Médio", "Faturamento Total"};
            String[] metodos = {"getHora", "getQuantidadePedidos", "getTicketMedio", "getFaturamentoTotal"};
            
            byte[] pdf = exportService.exportarPDF(dados, "Relatório de Horários de Pico", colunas, metodos);
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=horarios_pico.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/horarios-pico/csv")
    public ResponseEntity<byte[]> exportarHorariosPicoCSV() {
        try {
            List<HorariosPicoResponseDTO> dados = horariosPicoService.listarTodos();
            
            String[] colunas = {"Hora", "Quantidade Pedidos", "Ticket Médio", "Faturamento Total"};
            String[] metodos = {"getHora", "getQuantidadePedidos", "getTicketMedio", "getFaturamentoTotal"};
            
            byte[] csv = exportService.exportarCSV(dados, colunas, metodos);
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=horarios_pico.csv")
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(csv);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== ANÁLISE DE PAGAMENTOS ==========
    
    @GetMapping("/analise-pagamentos/{formato}")
    public ResponseEntity<byte[]> exportarAnalisePagamentos(@PathVariable String formato) {
        try {
            List<AnalisePagamentosResponseDTO> dados = analisePagamentosService.listarTodos();
            
            String[] colunas = {"Método Pagamento", "Total Transações", "Valor Total", "Ticket Médio"};
            String[] metodos = {"getMetodoPagamento", "getTotalTransacoes", "getValorTotal", "getTicketMedio"};
            
            if ("pdf".equalsIgnoreCase(formato)) {
                byte[] pdf = exportService.exportarPDF(dados, "Relatório de Análise de Pagamentos", colunas, metodos);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=analise_pagamentos.pdf")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(pdf);
            } else if ("csv".equalsIgnoreCase(formato)) {
                byte[] csv = exportService.exportarCSV(dados, colunas, metodos);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=analise_pagamentos.csv")
                        .contentType(MediaType.parseMediaType("text/csv"))
                        .body(csv);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== PRODUTOS BAIXA ROTATIVIDADE ==========
    
    @GetMapping("/produtos-baixa-rotatividade/{formato}")
    public ResponseEntity<byte[]> exportarProdutosBaixaRotatividade(@PathVariable String formato) {
        try {
            List<ProdutosBaixaRotatividadeResponseDTO> dados = produtosBaixaRotatividadeService.listarTodos();
            
            String[] colunas = {"ID", "Produto", "Categoria", "Preço", "Vendas 30 dias", "Receita 30 dias"};
            String[] metodos = {"getIdCardapio", "getProduto", "getCategoria", "getPreco", "getVendasUltimos30Dias", "getReceitaUltimos30Dias"};
            
            if ("pdf".equalsIgnoreCase(formato)) {
                byte[] pdf = exportService.exportarPDF(dados, "Relatório de Produtos com Baixa Rotatividade", colunas, metodos);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=produtos_baixa_rotatividade.pdf")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(pdf);
            } else if ("csv".equalsIgnoreCase(formato)) {
                byte[] csv = exportService.exportarCSV(dados, colunas, metodos);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=produtos_baixa_rotatividade.csv")
                        .contentType(MediaType.parseMediaType("text/csv"))
                        .body(csv);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RANKING DE CATEGORIAS ==========
    
    @GetMapping("/ranking-categorias/{formato}")
    public ResponseEntity<byte[]> exportarRankingCategorias(@PathVariable String formato) {
        try {
            List<RankingCategoriasResponseDTO> dados = rankingCategoriasService.listarTodos();
            
            String[] colunas = {"Categoria", "Total Produtos", "Total Unidades Vendidas", "Receita Total", "Preço Médio"};
            String[] metodos = {"getCategoria", "getTotalProdutos", "getTotalUnidadesVendidas", "getReceitaTotal", "getPrecoMedio"};
            
            if ("pdf".equalsIgnoreCase(formato)) {
                byte[] pdf = exportService.exportarPDF(dados, "Relatório de Ranking de Categorias", colunas, metodos);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ranking_categorias.pdf")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(pdf);
            } else if ("csv".equalsIgnoreCase(formato)) {
                byte[] csv = exportService.exportarCSV(dados, colunas, metodos);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ranking_categorias.csv")
                        .contentType(MediaType.parseMediaType("text/csv"))
                        .body(csv);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
