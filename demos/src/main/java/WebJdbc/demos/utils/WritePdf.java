package WebJdbc.demos.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import WebJdbc.demos.entity.SearchHivRow;

@Component
public class WritePdf {

	final String FONT = "C:/Users/eHealth-PC/Downloads/font-times-new-roman.ttf";
	final String parentPath = "C:/Users/eHealth-PC/Desktop/pdf";

	public void createFile(List<SearchHivRow> list)
			throws DocumentException, IllegalArgumentException, IllegalAccessException, IOException {
		Document document = new Document(PageSize.A3.rotate());
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(parentPath + "/" + "thongtinbn.pdf"));
		document.open();
		PdfPTable table = new PdfPTable(13);
		table.setWidthPercentage(100);
		table.setSpacingBefore(10f);
		table.setSpacingAfter(10f);
		float[] columnWidths = { 0.5f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f,1f };
		table.setWidths(columnWidths);
		try {
			document.add(new WritePdf().addHeaderManual());
			for (SearchHivRow row : list) {
				//document.add(new WritePdf().addRow(row));
				document.add(new WritePdf().addRowManual(row));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		document.close();
		writer.close();
	}

	public PdfPTable addHeader()
			throws IllegalArgumentException, IllegalAccessException, DocumentException, IOException {
		PdfPTable rowHeader = new PdfPTable(13);
		rowHeader.setWidthPercentage(100);
		rowHeader.setSpacingBefore(10f);
		rowHeader.setSpacingAfter(10f);
		float[] columnWidths = { 0.5f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f,1f };
		rowHeader.setWidths(columnWidths);

		SearchHivRow abc = new SearchHivRow();
		for (Field field : abc.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			String name = field.getName();
			BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font f = new Font(bf, 10);
			PdfPCell cell = new PdfPCell(new Paragraph(name.toUpperCase(), f));
			if (name.toString().toLowerCase().contains("serialversionuid"))
				continue;
			else {
				rowHeader.addCell(cell);
			}
		}
		return rowHeader;
	}

	public PdfPTable addRow(SearchHivRow row)
			throws IllegalArgumentException, IllegalAccessException, DocumentException, IOException {
		PdfPTable rowTable = new PdfPTable(13);
		rowTable.setWidthPercentage(100);
		float[] columnWidths = { 0.5f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f,1f };
		rowTable.setWidths(columnWidths);
		for (Field field : row.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value = field.get(row);
			String name = field.getName();
			BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font f = new Font(bf, 10);
			PdfPCell cell = new PdfPCell(new Paragraph(value.toString().toUpperCase(), f));
			cell.setLeft(5);
			if (name.toString().toLowerCase().contains("serialversionuid"))
				continue;
			else {
				rowTable.addCell(cell);
			}

		}
		return rowTable;

	}
	

	public PdfPTable addRowManual(SearchHivRow row)
			throws IllegalArgumentException, IllegalAccessException, DocumentException, IOException {
		PdfPTable rowTable = new PdfPTable(13);
		rowTable.setWidthPercentage(100);
		float[] columnWidths = { 0.5f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f,1f };
		rowTable.setWidths(columnWidths);
		BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font f = new Font(bf, 10);
		PdfPCell cell1 = new PdfPCell(new Paragraph(String.valueOf(row.getIndex()), f));
		rowTable.addCell(cell1);
		PdfPCell cell2 = new PdfPCell(new Paragraph(row.getId(), f));
		rowTable.addCell(cell2);
		PdfPCell cell3 = new PdfPCell(new Paragraph(row.getHoten(), f));
		rowTable.addCell(cell3);
		PdfPCell cell4 = new PdfPCell(new Paragraph(row.getGioitinh(), f));
		rowTable.addCell(cell4);
		PdfPCell cell5 = new PdfPCell(new Paragraph(row.getTuoi(), f));
		rowTable.addCell(cell5);
		PdfPCell cell6 = new PdfPCell(new Paragraph(row.getDiachi(), f));
		rowTable.addCell(cell6);
		PdfPCell cell7 = new PdfPCell(new Paragraph(row.getDoituong(), f));
		rowTable.addCell(cell7);
		PdfPCell cell8 = new PdfPCell(new Paragraph(row.getNgaylm(), f));
		rowTable.addCell(cell8);
		PdfPCell cell9 = new PdfPCell(new Paragraph(row.getNgayxn(), f));
		rowTable.addCell(cell9);
		PdfPCell cell10 = new PdfPCell(new Paragraph(row.getSp(), f));
		rowTable.addCell(cell10);
		PdfPCell cell11 = new PdfPCell(new Paragraph(row.getKetluan(), f));
		rowTable.addCell(cell11);
		PdfPCell cell12 = new PdfPCell(new Paragraph(row.getKetluankd(), f));
		rowTable.addCell(cell12);
		PdfPCell cell13 = new PdfPCell(new Paragraph(row.getGhichu(), f));
		rowTable.addCell(cell13);
		return rowTable;

	}
	
	
	public PdfPTable addHeaderManual()
			throws IllegalArgumentException, IllegalAccessException, DocumentException, IOException {
		PdfPTable rowHeader = new PdfPTable(13);
		rowHeader.setWidthPercentage(100);
		rowHeader.setSpacingBefore(10f);
		rowHeader.setSpacingAfter(10f);
		float[] columnWidths = { 0.5f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f,1f };
		rowHeader.setWidths(columnWidths);
		BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font f = new Font(bf, 10);
		PdfPCell cell1 = new PdfPCell(new Paragraph("Số thứ tự ", f));
		rowHeader.addCell(cell1);
		PdfPCell cell2 = new PdfPCell(new Paragraph("Mã BN ", f));
		rowHeader.addCell(cell2);
		PdfPCell cell3 = new PdfPCell(new Paragraph("Họ Và Tên ", f));
		rowHeader.addCell(cell3);
		PdfPCell cell4 = new PdfPCell(new Paragraph("Giới tính ", f));
		rowHeader.addCell(cell4);
		PdfPCell cell5 = new PdfPCell(new Paragraph("Tuổi  ", f));
		rowHeader.addCell(cell5);
		PdfPCell cell6 = new PdfPCell(new Paragraph("Địa chỉ  ", f));
		rowHeader.addCell(cell6);
		PdfPCell cell7 = new PdfPCell(new Paragraph("Đối tượng ", f));
		rowHeader.addCell(cell7);
		PdfPCell cell8 = new PdfPCell(new Paragraph("Ngày LM ", f));
		rowHeader.addCell(cell8);
		PdfPCell cell9 = new PdfPCell(new Paragraph("Ngày XN ", f));
		rowHeader.addCell(cell9);
		PdfPCell cell10 = new PdfPCell(new Paragraph("SP ", f));
		rowHeader.addCell(cell10);
		PdfPCell cell11 = new PdfPCell(new Paragraph("Kết quả  ", f));
		rowHeader.addCell(cell11);
		PdfPCell cell12 = new PdfPCell(new Paragraph("Kết quả KD ", f));
		rowHeader.addCell(cell12);
		PdfPCell cell13 = new PdfPCell(new Paragraph("Ghi chú  ", f));
		rowHeader.addCell(cell13);
		return rowHeader;
	}

}
