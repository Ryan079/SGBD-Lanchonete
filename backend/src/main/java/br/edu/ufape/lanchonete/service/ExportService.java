package br.edu.ufape.lanchonete.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

/**
 * Serviço para exportação de relatórios em diferentes formatos (PDF, CSV).
 */
@Service
public class ExportService {

    /**
     * Exporta uma lista de dados para formato PDF.
     * 
     * @param dados Lista de objetos a serem exportados
     * @param titulo Título do relatório
     * @param colunas Nomes das colunas
     * @param metodos Métodos getter dos objetos para obter os valores
     * @return Byte array do PDF gerado
     */
    public byte[] exportarPDF(List<?> dados, String titulo, String[] colunas, String[] metodos) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Adiciona título
        document.add(new Paragraph(titulo).setFontSize(18).setBold());

        if (dados.isEmpty()) {
            document.add(new Paragraph("Nenhum dado encontrado."));
        } else {
            // Cria tabela
            Table table = new Table(colunas.length);
            
            // Adiciona cabeçalhos
            for (String coluna : colunas) {
                table.addHeaderCell(coluna);
            }

            // Adiciona dados
            for (Object obj : dados) {
                for (String metodo : metodos) {
                    Object valor = invocarGetter(obj, metodo);
                    table.addCell(valor != null ? valor.toString() : "");
                }
            }

            document.add(table);
        }

        document.close();
        return baos.toByteArray();
    }

    /**
     * Exporta uma lista de dados para formato CSV.
     * 
     * @param dados Lista de objetos a serem exportados
     * @param colunas Nomes das colunas
     * @param metodos Métodos getter dos objetos para obter os valores
     * @return Byte array do CSV gerado
     */
    public byte[] exportarCSV(List<?> dados, String[] colunas, String[] metodos) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(baos);
        
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(colunas)
                .build();
        
        try (CSVPrinter printer = new CSVPrinter(writer, csvFormat)) {
            for (Object obj : dados) {
                Object[] valores = new Object[metodos.length];
                for (int i = 0; i < metodos.length; i++) {
                    try {
                        valores[i] = invocarGetter(obj, metodos[i]);
                    } catch (Exception e) {
                        valores[i] = "";
                    }
                }
                printer.printRecord(valores);
            }
            printer.flush();
        }

        return baos.toByteArray();
    }

    /**
     * Invoca um método getter de um objeto usando reflexão.
     */
    private Object invocarGetter(Object obj, String nomeMetodo) throws Exception {
        Method method = obj.getClass().getMethod(nomeMetodo);
        return method.invoke(obj);
    }
}
