package com.kraftmatic.geodesgemstones.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class PDFGenerator {

    public void generateIndexCardPdf() throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("infoCard.pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
        Chunk chunk = new Chunk("test", font);
        document.add(chunk);
        document.close();
    }
}
