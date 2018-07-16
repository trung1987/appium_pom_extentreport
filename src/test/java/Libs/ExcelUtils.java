package Libs;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static Workbook newWorkbook(String filePath, String fileName) throws IOException {
		File file = new File(filePath + "//" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equalsIgnoreCase(".xlsx")) {
			wb = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equalsIgnoreCase(".xls")) {
			wb = new HSSFWorkbook(inputStream);
		}
		return wb;
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	public static ArrayList<String> readExcelFileAtColumn(String filePath, String fileName, String sheetName,
			int column) throws IOException {
		ArrayList<String> columnData = new ArrayList<String>();
		Workbook wb = newWorkbook(filePath, fileName);
		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		for (int i = 0; i < rowCount + 1; i++) {
			try {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(column);
				cell.setCellType(cell.CELL_TYPE_STRING);
				columnData.add(cell.getStringCellValue());
			} catch (Exception e) {
				// if row(i) = null / empty
				columnData.add("");
			}
		}
		return columnData;
	}

	// read data at specific row from 'startColumn' to 'endColumn'
	@SuppressWarnings({ "deprecation", "static-access" })
	public static ArrayList<String> readExcelFileAtRow(String filePath, String fileName, String sheetName, int row,
			int startColumn, int endColumn) throws IOException {
		ArrayList<String> rowData = new ArrayList<String>();
		try {
			Workbook wb = newWorkbook(filePath, fileName);
			Sheet sheet = wb.getSheet(sheetName);
			Row rowExcel = null;
			rowExcel = sheet.getRow(row);
			for (int i = startColumn; i <= endColumn; i++) {
				try {
					Cell cell = rowExcel.getCell(i);
					cell.setCellType(cell.CELL_TYPE_STRING);
					rowData.add(cell.getStringCellValue());
				} catch (Exception e) {
					// if row(i) = null / empty
					rowData.add("");
				}
			}
		} catch (Exception e) {
			System.out.println("Cannot read data at row:" + row);
		}
		return rowData;
	}

	/*
	 * Read excel file with all data
	 */
	public static ArrayList<ArrayList<String>> readExcelAllData(String filePath, String fileName, String sheetName)
			throws IOException {
		ArrayList<ArrayList<String>> arrSum = new ArrayList<>();
		Workbook wb = newWorkbook(filePath, fileName);
		Sheet sheet = wb.getSheet(sheetName);
		// read each row then add it into a ArrayList (read from row 0 to last
		// row)
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			ArrayList<String> rowData = readExcelFileAtRow(filePath, fileName, sheetName, i, 0,
					sheet.getRow(0).getLastCellNum());
			arrSum.add(rowData);
		}
		return arrSum;
	}
	
	@SuppressWarnings("deprecation")
	public static String getDataAtCell(String filePath, String fileName, String sheetName, int row, int column) throws IOException{
		Workbook wb = newWorkbook(filePath, fileName);
		String CellData;
		Sheet sheet = wb.getSheet(sheetName);		
		try {
			Row rowExcel = sheet.getRow(row);
			Cell cell = rowExcel.getCell(column);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			CellData = cell.getStringCellValue();
		} catch (Exception e) {
			CellData = "";
		}
		return CellData;
	}
	
}
