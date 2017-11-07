package com.infrrd.testfrog.helper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {

	public String path;
	FileInputStream fis;
	FileOutputStream fos;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;

	public ExcelHelper(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getRowNumber(String sheetName, String colName, String rowValue){
		try {
			int rowNum = 0;
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equalsIgnoreCase(colName)) {
					col_Num = i;
				}
			}
			for(int i = 0 ; i < getRowCount(sheetName) ; i++){
				XSSFRow tempRow = sheet.getRow(i);
				if (tempRow.getCell(col_Num).getStringCellValue().equalsIgnoreCase(rowValue)){
					rowNum = i + 1;
				}
			}
			return rowNum;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getRowNumber(String sheetName, int col_Num, String rowValue){
		try {
			int rowNum = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			
			for(int i = 0 ; i < getRowCount(sheetName) ; i++){
				XSSFRow tempRow = sheet.getRow(i);
				if (tempRow.getCell(col_Num).getStringCellValue().equalsIgnoreCase(rowValue)){
					rowNum = i + 1;
				}
			}
			return rowNum;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void setFont(String sheetName, int rowNum, String colName, String colour, boolean bold, boolean italic){
		try {
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equalsIgnoreCase(colName)) {
					col_Num = i;
				}
			}

			Cell cell = null;

			XSSFRow sheetrow = sheet.getRow(rowNum - 1);

			if (sheetrow == null) {
				sheetrow = sheet.createRow(rowNum - 1);
			}

			cell = sheetrow.getCell(col_Num);

			if (cell == null) {
				cell = sheetrow.createCell(col_Num);
			}

			// Setting font
			
			XSSFFont font = workbook.createFont();

			XSSFCellStyle style = workbook.createCellStyle();
			
			if(!colour.equalsIgnoreCase("NA")){
				if (colour.equalsIgnoreCase("Red")) {
					font.setColor(IndexedColors.RED.getIndex());
				}

				else if (colour.equalsIgnoreCase("Green")) {
					font.setColor(IndexedColors.GREEN.getIndex());
				}
				
				else if(colour.equalsIgnoreCase("Yellow")){
					font.setColor(IndexedColors.YELLOW.getIndex());
				}
			}
									
			font.setBold(bold);
			font.setItalic(italic);
			style.setFont(font);
			cell.setCellStyle(style);

			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setFont(String sheetName, int rowNum, int col_Num, String colour, boolean bold, boolean italic){
		try {
			
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			
			Cell cell = null;

			XSSFRow sheetrow = sheet.getRow(rowNum - 1);

			if (sheetrow == null) {
				sheetrow = sheet.createRow(rowNum - 1);
			}

			cell = sheetrow.getCell(col_Num);

			if (cell == null) {
				cell = sheetrow.createCell(col_Num);
			}

			// Setting font
			
			XSSFFont font = workbook.createFont();

			XSSFCellStyle style = workbook.createCellStyle();
			
			if(!colour.equalsIgnoreCase("NA")){
				if (colour.equalsIgnoreCase("Red")) {
					font.setColor(IndexedColors.RED.getIndex());
				}

				else if (colour.equalsIgnoreCase("Green")) {
					font.setColor(IndexedColors.GREEN.getIndex());
				}
				
				else if(colour.equalsIgnoreCase("Yellow")){
					font.setColor(IndexedColors.YELLOW.getIndex());
				}
			}
									
			font.setBold(bold);
			font.setItalic(italic);
			style.setFont(font);
			cell.setCellStyle(style);

			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeCellValue(String sheetName, int rowNum, String colName, String message){
		try {
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equalsIgnoreCase(colName)) {
					col_Num = i;
				}
			}

			Cell cell = null;

			XSSFRow sheetrow = sheet.getRow(rowNum - 1);

			if (sheetrow == null) {
				sheetrow = sheet.createRow(rowNum - 1);
			}

			cell = sheetrow.getCell(col_Num);

			if (cell == null) {
				cell = sheetrow.createCell(col_Num);
			}

			// Setting style

			cell.setCellValue(message);

			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void writeCellValue(String sheetName, int rowNum, int col_Num, String message){
		try {
			
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			
			Cell cell = null;

			XSSFRow sheetrow = sheet.getRow(rowNum - 1);

			if (sheetrow == null) {
				sheetrow = sheet.createRow(rowNum - 1);
			}

			cell = sheetrow.getCell(col_Num);

			if (cell == null) {
				cell = sheetrow.createCell(col_Num);
			}

			// Setting style

			cell.setCellValue(message);

			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void setCellColour(String sheetName, int rowNum, String colName, String colour) {
		try {
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equalsIgnoreCase(colName)) {
					col_Num = i;
				}
			}

			Cell cell = null;

			XSSFRow sheetrow = sheet.getRow(rowNum - 1);

			if (sheetrow == null) {
				sheetrow = sheet.createRow(rowNum - 1);
			}

			cell = sheetrow.getCell(col_Num);

			if (cell == null) {
				cell = sheetrow.createCell(col_Num);
			}

			// Setting style

			XSSFCellStyle style = workbook.createCellStyle();

			if (colour.equalsIgnoreCase("Red")) {
				style.setFillBackgroundColor(IndexedColors.RED.getIndex());
			}

			else if (colour.equalsIgnoreCase("Green")) {
				style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
			}
			
			else if(colour.equalsIgnoreCase("Yellow")){
				style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
			}

			style.setFillPattern(XSSFCellStyle.BORDER_SLANTED_DASH_DOT);

			cell.setCellStyle(style);

			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setCellColour(String sheetName, int rowNum, int col_Num, String colour) {
		try {
			
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			
			Cell cell = null;

			XSSFRow sheetrow = sheet.getRow(rowNum - 1);

			if (sheetrow == null) {
				sheetrow = sheet.createRow(rowNum - 1);
			}

			cell = sheetrow.getCell(col_Num);

			if (cell == null) {
				cell = sheetrow.createCell(col_Num);
			}

			// Setting style

			XSSFCellStyle style = workbook.createCellStyle();

			if (colour.equalsIgnoreCase("Red")) {
				style.setFillBackgroundColor(IndexedColors.RED.getIndex());
			}

			else if (colour.equalsIgnoreCase("Green")) {
				style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
			}
			
			else if(colour.equalsIgnoreCase("Yellow")){
				style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
			}

			style.setFillPattern(XSSFCellStyle.BORDER_SLANTED_DASH_DOT);

			cell.setCellStyle(style);

			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String getCellData(String sheetName, int rowNum, String colName) {
		try {

			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equalsIgnoreCase(colName)) {
					col_Num = i;
				}
			}
			row = sheet.getRow(rowNum - 1);
			XSSFCell cell = row.getCell(col_Num);
			if(cell == null){
				return "";
			} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getCellData(String sheetName, int rowNum, int colNum) {
		try {

			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			row = sheet.getRow(rowNum - 1);
			XSSFCell cell = row.getCell(colNum - 1);
			if(cell == null){
				return "";
			} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(Math.round(cell.getNumericCellValue()));
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			} else {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public int getRowCount(String sheetName) {
		try {

			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workbook.getSheetAt(index);
				int number = sheet.getLastRowNum() + 1;
				return number;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getColumnCount(String sheetName) {
		try {

			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workbook.getSheet(sheetName);
				row = sheet.getRow(0);
				return row.getLastCellNum();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Set<String> getUniqueColumnItem(String sheetName, String colName) {
		try {

			Set<String> uniqueItems = new HashSet<String>();
			int col_Num = 0;
			int rowNum;
			String cellValue = null;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equalsIgnoreCase(colName)) {
					col_Num = i;
				}
			}

			int rowCount = getRowCount(sheetName);

			for (rowNum = 2; rowNum <= rowCount; rowNum++) {
				row = sheet.getRow(rowNum - 1);
				XSSFCell cell = row.getCell(col_Num);
				if(cell == null){
					cellValue = "";
				} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					cellValue = cell.getStringCellValue();
				} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					cellValue = String.valueOf(cell.getNumericCellValue());
				} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
					cellValue = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
					cellValue = "";
				}
				uniqueItems.add(cellValue);
			}

			return uniqueItems;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public Set<String> getUniqueColumnItem(String sheetName, int colNum) {
		try {

			Set<String> uniqueItems = new HashSet<String>();

			int rowNum;
			String cellValue = null;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);

			int rowCount = getRowCount(sheetName);

			for (rowNum = 2; rowNum <= rowCount; rowNum++) {
				row = sheet.getRow(rowNum - 1);
				XSSFCell cell = row.getCell(colNum);
				if(cell == null){
					cellValue = "";
				} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					cellValue = cell.getStringCellValue();
				} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					cellValue = String.valueOf(cell.getNumericCellValue());
				} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
					cellValue = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
					cellValue = "";
				}
				uniqueItems.add(cellValue);
			}

			return uniqueItems;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	

}
