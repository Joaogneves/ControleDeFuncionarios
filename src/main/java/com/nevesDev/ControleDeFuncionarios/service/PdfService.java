package com.nevesDev.ControleDeFuncionarios.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nevesDev.ControleDeFuncionarios.model.employee.Employee;
import com.nevesDev.ControleDeFuncionarios.model.workhour.Workhour;
import com.nevesDev.ControleDeFuncionarios.model.workmonth.Workmonth;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class PdfService {
    public static void generate(Employee e, Workmonth w) {
        List<Workhour> ws = e.getWorkhours();
        ws.sort(new Comparator<Workhour>() {
            @Override
            public int compare(Workhour wh1, Workhour wh2) {
                return wh1.getWorkDay().compareTo(wh2.getWorkDay());
            }
            });
        Font font12 = FontFactory.getFont(FontFactory.HELVETICA, 12);
        Font font10as = FontFactory.getFont(FontFactory.COURIER, 10);
        Font font10 = FontFactory.getFont(FontFactory.HELVETICA, 10);
        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font font4 = FontFactory.getFont(FontFactory.HELVETICA, 4);
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("src/main/resources/docs/"+ w.getMonth() +"/"+ e.getFirstName() + " " + e.getLastName() +".pdf"));
            float width = document.getPageSize().getWidth();
            float height = document.getPageSize().getHeight();
            document.open();
            float[] columnDefinitionSize = {33.33F, 33.33F, 33.33F};

            float pos = height / 2;
            PdfPTable table = null;
            PdfPCell cell = null;

            table = new PdfPTable(1);
            table.getDefaultCell().setBorder(0);
            table.setHorizontalAlignment(0);
            table.setTotalWidth(width - 72);
            table.setLockedWidth(true);
            table.getDefaultCell().setPadding(10);

            cell = new PdfPCell(new Phrase("FOLHA DE PONTO"));
            cell.setColspan(columnDefinitionSize.length);
            table.addCell(cell);
            table.addCell(new Phrase("NOME: " + e.getFirstName() + " " + e.getLastName(), font12));
            table.addCell(new Phrase("CPF: " + e.getCpf() , font12));
            table.addCell(new Phrase("CNPJ: " + e.getCnpj(), font12));
            table.addCell(new Phrase("FUNÇÃO: " + e.getFuncao(), font12));
            table.addCell(new Phrase("LOCAL DE TRABALHO: " + e.getWorkPlace(), font12));
            document.add(table);

            table = new PdfPTable(columnDefinitionSize);
            table.getDefaultCell().setBorder(0);
            table.setHorizontalAlignment(0);
            table.setTotalWidth(width - 72);
            table.setLockedWidth(true);

            cell = new PdfPCell(new Phrase("HORARIOS"));
            cell.setColspan(columnDefinitionSize.length);
            table.getDefaultCell().setPaddingBottom(25);
            table.addCell(cell);
            document.add(table);

            table = new PdfPTable(8);
            table.getDefaultCell().setBorder(2);
            table.setHorizontalAlignment(0);
            table.setTotalWidth(width - 72);
            table.setLockedWidth(true);
            table.getDefaultCell().setPaddingTop(25);

            table.addCell(new Phrase("Data", font10));
            table.addCell(new Phrase("Entrada", font10));
            table.addCell(new Phrase("Almoço", font10));
            table.addCell(new Phrase("Retorno", font10));
            table.addCell(new Phrase("Saida", font10));
            table.addCell(new Phrase("Inicio ext", font10));
            table.addCell(new Phrase("Fim ext", font10));
            table.addCell(new Phrase("Tipo", font10));
            document.add(table);
            for(Workhour wh : ws) {
                table = new PdfPTable(8);
                table.getDefaultCell().setBorder(1);
                table.getDefaultCell().setPadding(3);
                table.setHorizontalAlignment(0);
                table.setTotalWidth(width - 72);
                table.setLockedWidth(true);
                cell.setColspan(columnDefinitionSize.length);
                table.addCell(new Phrase(wh.getWorkDay().toString(), font8));
                table.addCell(new Phrase(wh.getEntry().toString(), font8));
                table.addCell(new Phrase(wh.getBreakInit().toString(), font8));
                table.addCell(new Phrase(wh.getBreakEnd().toString(), font8));
                table.addCell(new Phrase(wh.getLeave().toString(), font8));
                table.addCell(new Phrase(wh.getStartExtra() != null ? wh.getStartExtra().toString() : "-" , font8));
                table.addCell(new Phrase(wh.getEndExtra() != null ? wh.getEndExtra().toString() : "-", font8));
                table.addCell(new Phrase(wh.getWorkhourStatus().toString(), font4));
                document.add(table);
            }

            table = new PdfPTable(7);
            table.getDefaultCell().setBorder(2);
            table.setHorizontalAlignment(0);
            table.setTotalWidth(width - 72);
            table.setLockedWidth(true);
            table.getDefaultCell().setPadding(10);

            table.addCell(new Phrase("Mês", font10));
            table.addCell(new Phrase("Horas normais", font10));
            table.addCell(new Phrase("Min normais", font10));
            table.addCell(new Phrase("Horas 50%", font10));
            table.addCell(new Phrase("Min 50%", font10));
            table.addCell(new Phrase("Horas 100%", font10));
            table.addCell(new Phrase("Min 100%", font10));
            table.addCell(new Phrase(w.getMonth(), font8));
            table.addCell(new Phrase(w.getNormalHour()+"", font8));
            table.addCell(new Phrase(w.getNormalMinutes()+"", font8));
            table.addCell(new Phrase(w.getExtraHour50()+"", font8));
            table.addCell(new Phrase(w.getExtraMinutes50()+"", font8));
            table.addCell(new Phrase(w.getExtraHour100()+"", font8));
            table.addCell(new Phrase(w.getExtraMinutes100()+"", font8));

            document.add(table);

            table = new PdfPTable(2);
            table.getDefaultCell().setBorder(0);
            table.setHorizontalAlignment(0);
            table.setTotalWidth(width - 72);
            table.setLockedWidth(true);
            table.getDefaultCell().setPaddingTop(25);

            table.addCell(new Phrase("______________________________________", font10as));
            table.addCell(new Phrase("______________________________________", font10as));
            table.addCell(new Phrase("          " + e.getFirstName() + " " + e.getLastName(), font10));
            table.addCell(new Phrase("                            Supervisor", font10));

            document.add(table);
        } catch (DocumentException | IOException de) {
            System.err.println(de.getMessage());
        }
        // step 5
        document.close();
    }
}