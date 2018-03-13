/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacher;

import Model.Semester;
import Model.Specialized;
import Model.StudentInfoModel;
import Model.StudentMarkModel;
import Model.Subject;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author vomin
 */
public class PDFExport {

    public static final String FONT = "FreeSans.ttf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 20,
            Font.BOLD);

    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 13,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.BOLD);

    public void ExportPDF(String MSSV) {
        try {
            String FILE = "D:/Aptech/MarkTable_" + MSSV + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document, MSSV);
            addTitlePage(document, MSSV);
            addContent(document, MSSV);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document, String MSSV) {
        document.addTitle("TableMark For Student " + MSSV);
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Minh Phat");
        document.addCreator("Minh Phat");
    }

    private static void addTitlePage(Document document, String MSSV)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        Paragraph paragraph = new Paragraph("STUDENT GRADE TABLE", catFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 2);

        TeacherDAO teacherDao = new TeacherDAO();

        StudentInfoModel detailStudentInfo = teacherDao.getDetailStudentInfo(MSSV);

        Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, 13, Font.BOLD);

        preface.add(new Paragraph("MSSV: " + MSSV, font));

        preface.add(new Paragraph("Name: " + detailStudentInfo.FullName, font));

        Specialized detailSpecializedInfo = teacherDao.getDetailSpecializedInfo(detailStudentInfo.SpecializedID);

        preface.add(new Paragraph("Specialized: " + detailSpecializedInfo.Name, font));

        preface.add(new Paragraph("Education: " + detailStudentInfo.KindEducation, font));

        addEmptyLine(preface, 2);

        document.add(preface);
        // Start a new page
        //document.newPage();
    }

    private static void addContent(Document document, String MSSV) throws DocumentException, BadElementException, IOException {
//        Anchor anchor = new Anchor("First Chapter", catFont);
//        anchor.setName("First Chapter");
//
//        // Second parameter is the number of the chapter
//        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
//
//        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
//        Section subCatPart = catPart.addSection(subPara);
//        subCatPart.add(new Paragraph("Hello"));
//
//        subPara = new Paragraph("Subcategory 2", subFont);
//        subCatPart = catPart.addSection(subPara);
//        subCatPart.add(new Paragraph("Paragraph 1"));
//        subCatPart.add(new Paragraph("Paragraph 2"));
//        subCatPart.add(new Paragraph("Paragraph 3"));
//
//        // add a list
//        createList(subCatPart);
//        Paragraph paragraph = new Paragraph();
//        addEmptyLine(paragraph, 5);
//        subCatPart.add(paragraph);

        // add a table
        createTable(document, MSSV);

//        // now add all this to the document
//        document.add(catPart);
//
//        // Next section
//        anchor = new Anchor("Second Chapter", catFont);
//        anchor.setName("Second Chapter");
//
//        // Second parameter is the number of the chapter
//        catPart = new Chapter(new Paragraph(anchor), 1);
//
//        subPara = new Paragraph("Subcategory", subFont);
//        subCatPart = catPart.addSection(subPara);
//        subCatPart.add(new Paragraph("This is a very important message"));
//
//        // now add all this to the document
//        document.add(catPart);
    }

    private static void createTable(Document document, String MSSV)
            throws BadElementException, DocumentException, IOException {

        float[] columnWidths = {2, 2, 4, 1, 1, 1};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);

        // t.setBorderColor(BaseColor.GRAY);
        //t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, 13, Font.BOLD);

        PdfPCell c1 = new PdfPCell(new Phrase("Term", font));

        c1.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("MMH"));

        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Name"));

        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Credits"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

//        c1 = new PdfPCell(new Phrase("Mid Marks"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//        c1 = new PdfPCell(new Phrase("Final Marks"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("AVG"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Type"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        //lấy từ bảng điểm
        TeacherDAO teacherDao = new TeacherDAO();
        ArrayList<StudentMarkModel> stm = teacherDao.SearchStuentMark(MSSV, null, null);

        int NC = 0;
        float TotalM = 0;
        int TotalF = 0;
        for (int i = 0; i < stm.size(); i++) {
            Subject detail = teacherDao.getDetailSubjectInfo(stm.get(i).MMH);
            
            Semester sm=teacherDao.getDetailSemester(stm.get(i).SemesterID);
            table.addCell(sm.Name);
            table.addCell(stm.get(i).MMH);

            table.addCell(getNormalCell(detail.Name, 12));
            table.addCell(Integer.toString(detail.NumberOfCredits));
            NC += detail.NumberOfCredits;
            table.addCell(Float.toString(stm.get(i).AVGPoint));
            TotalM += stm.get(i).AVGPoint*detail.NumberOfCredits;

            if (stm.get(i).Type.equals("Fail")) {
                TotalF += 1;
            }
            table.addCell(stm.get(i).Type);

        }
        document.add(table);

        Paragraph preface = new Paragraph();

        preface.add(new Paragraph("Total Subject: " + stm.size(), font));

        preface.add(new Paragraph("Total Credits: " + NC, font));

        preface.add(new Paragraph("AVG: " + (double)Math.round(TotalM/NC * 10) / 10, font));

        preface.add(new Paragraph("Number Subject Fail: "+TotalF, font));

        document.add(preface);

    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public static PdfPCell getNormalCell(String string, float size)
            throws DocumentException, IOException {
        if (string != null && "".equals(string)) {
            return new PdfPCell();
        }
        Font f = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, 13, Font.NORMAL);

        if (size < 0) {
            f.setColor(BaseColor.RED);
            size = -size;
        }
        f.setSize(size);
        PdfPCell cell = new PdfPCell(new Phrase(string, f));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }
}
