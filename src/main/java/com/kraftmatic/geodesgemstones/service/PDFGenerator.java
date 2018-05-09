package com.kraftmatic.geodesgemstones.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Component
public class PDFGenerator {

    public void generateIndexCardPdf(String photoUrl, String title, String location, String type, String body) throws DocumentException, IOException {
        Document document = new Document(new Rectangle(500,300));
        document.setMargins(10,10,10,10);
        PdfWriter.getInstance(document, new FileOutputStream("infoCard.pdf"));
        document.open();


        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new int[] {1, 5});

        Image pic = Image.getInstance(new URL(photoUrl));
        pic.scaleAbsolute(120,120);
        PdfPCell topRight = new PdfPCell(pic);
        topRight.setBorder(PdfPCell.NO_BORDER);
        table.addCell(topRight);

        PdfPCell infoCell = generateInfoPane(title, location, type);
        infoCell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(infoCell);

        PdfPCell bottomText = new PdfPCell(new Phrase(body,
                new Font(FontFactory.getFont(FontFactory.HELVETICA, 14))));
        bottomText.setColspan(2);
        bottomText.setBorder(PdfPCell.NO_BORDER);
        bottomText.setPadding(20);
        table.addCell(bottomText);

        document.add(table);
        document.close();
    }

    private PdfPCell generateInfoPane(String title, String location, String type) {
        PdfPCell infoCell = new PdfPCell();
        PdfPTable infoTable = new PdfPTable(1);

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLACK);
        PdfPCell titleCell = new PdfPCell(new Phrase(title, titleFont));
        titleCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        titleCell.setPaddingBottom(20);
        titleCell.setBorder(PdfPCell.NO_BORDER);
        infoTable.addCell(titleCell);

        if (StringUtils.isNotBlank(location)) {
            Font typeFont = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);
            PdfPCell typeCell = new PdfPCell(new Phrase("Type: " + type, typeFont));
            typeCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            typeCell.setBorder(PdfPCell.NO_BORDER);
            infoTable.addCell(typeCell);
        }

        if(StringUtils.isNotBlank(type)) {
            Font locationFont = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);
            PdfPCell locationCell = new PdfPCell(new Phrase("Location: " + location, locationFont));
            locationCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            locationCell.setBorder(PdfPCell.NO_BORDER);
            infoTable.addCell(locationCell);
        }

        infoCell.addElement(infoTable);
        infoCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        return infoCell;
    }
}
