
package com.pollitica_Stat.ServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.pollitica_Stat.Model.VotersDetails;
import com.pollitica_Stat.Service.ExcelExportService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExcelExportServiceImpl implements ExcelExportService {

	 private static final String[] COLUMNS = {
	            "Voter ID",
	            "Voter Name (EN)",
	            "Voter Name (Marathi)",
	            "Father Name (EN)",
	            "Father Name (Marathi)",
	            "Age",
	            "House No",
	            "Gender",
	            "Prabhag ID"
	    };

	    @Override
	    public void exportVoters(List<VotersDetails> voters, String fileName, HttpServletResponse response) throws IOException {
	        Objects.requireNonNull(voters, "voters list must not be null");
	        if (fileName == null || fileName.isBlank()) {
	            fileName = "voters_export";
	        }

	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

	        // Use try-with-resources so workbook is closed automatically
	        try (Workbook workbook = new XSSFWorkbook()) {
	            XSSFSheet sheet = (XSSFSheet) workbook.createSheet("Voters Data");

	            // Header row and style
	            Row headerRow = sheet.createRow(0);
	            CellStyle headerStyle = workbook.createCellStyle();
	            XSSFFont font = (XSSFFont) workbook.createFont();
	            font.setBold(true);
	            headerStyle.setFont(font);

	            for (int i = 0; i < COLUMNS.length; i++) {
	                Cell cell = headerRow.createCell(i);
	                cell.setCellValue(COLUMNS[i]);
	                cell.setCellStyle(headerStyle);
	            }

	            // Data rows
	            int rowNum = 1;
	            for (VotersDetails v : voters) {
	                Row row = sheet.createRow(rowNum++);
	                row.createCell(0).setCellValue(nullToEmpty(v.getVoterId()));
	                row.createCell(1).setCellValue(nullToEmpty(v.getVoterEnglishName()));
	                row.createCell(2).setCellValue(nullToEmpty(v.getVoterMarathiName()));
	                row.createCell(3).setCellValue(nullToEmpty(v.getVotersFatherEnglishName()));
	                row.createCell(4).setCellValue(nullToEmpty(v.getVotersFatherMarathiName()));
	                row.createCell(5).setCellValue(nullToEmpty(v.getAge()));
	                row.createCell(6).setCellValue(nullToEmpty(v.getHouseNo()));
	                row.createCell(7).setCellValue(nullToEmpty(v.getGender()));
	                
	                    row.createCell(8).setCellValue(v.getPrabhag().getPrabhagId());
	              
	                    row.createCell(8).setCellValue("");
	            }

	            // Auto-size columns
	            for (int i = 0; i < COLUMNS.length; i++) {
	                sheet.autoSizeColumn(i);
	            }

	            // Write workbook to response output stream
	            ServletOutputStream outputStream = response.getOutputStream();
	            workbook.write(outputStream);
	            outputStream.flush();
	        }
	    }

	    private String nullToEmpty(Object obj) {
	        return obj == null ? "" : obj.toString();
	    }
	}


