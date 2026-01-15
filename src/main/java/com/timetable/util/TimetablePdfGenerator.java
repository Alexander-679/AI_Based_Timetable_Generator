package com.timetable.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.timetable.model.Timetable;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.awt.Color;

public class TimetablePdfGenerator {

    public static byte[] generatePdf(List<Timetable> timetables) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("AI Generated Timetable", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("\n"));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            addHeader(table, "Day");
            addHeader(table, "Time");
            addHeader(table, "Subject");
            addHeader(table, "Teacher");
            addHeader(table, "Room");

            for (Timetable t : timetables) {
                table.addCell(t.getTimeSlot().getDay());
                table.addCell(
                        t.getTimeSlot().getStartTime() + " - " +
                        t.getTimeSlot().getEndTime()
                );
                table.addCell(t.getSubject().getName());
                table.addCell(t.getTeacher().getName());
                table.addCell(t.getRoom().getRoomName());
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }

    private static void addHeader(PdfPTable table, String text) {
        PdfPCell header = new PdfPCell(new Phrase(text));
        header.setBackgroundColor(Color.LIGHT_GRAY);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(header);
    }
}
