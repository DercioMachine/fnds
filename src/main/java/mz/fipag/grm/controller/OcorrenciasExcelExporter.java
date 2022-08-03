package mz.fipag.grm.controller;

import mz.fipag.grm.domain.Ocorrencia;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class OcorrenciasExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Ocorrencia> listOcorrencia;

    public OcorrenciasExcelExporter(List<Ocorrencia> listOcorrencia) {

        this.listOcorrencia = listOcorrencia;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {

        sheet = workbook.createSheet("Ocorrencias");
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Número", style);
        createCell(row, 1, "Data Lançamento", style);
        createCell(row, 2, "Data Ocorrência", style);
        createCell(row, 3, "Descrição do Assunto", style);
        createCell(row, 4, "Canal", style);
        createCell(row, 5, "Utilizador", style);
        createCell(row, 6, "Projecto", style);
        createCell(row, 7, "Província", style);
        createCell(row, 8, "Distrito", style);
        createCell(row, 9, "Tipo Registo", style);
        createCell(row, 10, "Procedente", style);
        createCell(row, 11, "Nível da resolução", style);
        createCell(row, 12, "Detalhes da improcedência", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Ocorrencia ocorrencia : listOcorrencia) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            String resolucao = "ABC";

            if(ocorrencia.getResolucao().equals("P")){
                resolucao ="Pendente";
            } else if (ocorrencia.getResolucao().equals("V")) {
                resolucao ="Validado";
            }else if (ocorrencia.getResolucao().equals("R")) {
                resolucao ="Em Resolução";
            }else if (ocorrencia.getResolucao().equals("A")) {
                resolucao ="Em Acompanhamento";
            }else if (ocorrencia.getResolucao().equals("T")) {
                resolucao ="Resolvido";
            }

            SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
            String dataLancamento= DateFor.format(ocorrencia.getCreated());
            String dataOcorrencia= DateFor.format(ocorrencia.getDataOcorrencia());

            createCell(row, columnCount++, ocorrencia.getNumeroordem(), style);
            createCell(row, columnCount++, dataLancamento, style);
            createCell(row, columnCount++, dataOcorrencia, style);
            createCell(row, columnCount++, ocorrencia.getDescricao(), style);
            createCell(row, columnCount++, ocorrencia.getFormaComunicacao(), style);
            createCell(row, columnCount++, ocorrencia.getResponsavel()==null ? null : ocorrencia.getResponsavel().getNome(), style);
            createCell(row, columnCount++, ocorrencia.getProjecto().getDesignacao(), style);
            createCell(row, columnCount++, ocorrencia.getProvincia().getDesignacao(), style);
            createCell(row, columnCount++, ocorrencia.getDistrito().getDesignacao(), style);
            createCell(row, columnCount++, ocorrencia.getTipoOcorrencia()==null ? null : ocorrencia.getTipoOcorrencia().getDesignacao(), style);
            createCell(row, columnCount++, ocorrencia.getProcedencia(), style);
            createCell(row, columnCount++, resolucao , style);
            createCell(row, columnCount++, ocorrencia.getObservacao(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        
        outputStream.close();

    }

}
