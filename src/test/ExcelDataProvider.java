package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelDataProvider {

    public static List<Object[]> readExcelData(String filePath, String sheetName) throws IOException {
        List<Object[]> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null || row.getPhysicalNumberOfCells() < 6) {
                continue; // Skip empty rows or rows with insufficient cells
            }

            Object[] rowData = new Object[6];
            rowData[0] = getCellValue(row.getCell(0)); // testID
            rowData[1] = getCellValue(row.getCell(1)); // function
            rowData[2] = getCellValue(row.getCell(2)); // description
            rowData[3] = getCellValue(row.getCell(3)); // username
            rowData[4] = getCellValue(row.getCell(4)); // password

            // Read expected value
            rowData[5] = getExpectedBooleanValue(row.getCell(5)); // expected

            // Print debug info
            System.out.println("Row " + rowIndex + " Data: " + java.util.Arrays.toString(rowData));
            data.add(rowData);
        }
        workbook.close();
        return data;
    }

    private static Object getCellValue(Cell cell) {
        if (cell == null) {
            return null; // Return null if the cell is empty
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue(); // Return numeric directly
            case BOOLEAN:
                return cell.getBooleanCellValue(); // Return boolean directly
            default:
                return null; // Return null for unsupported cell types
        }
    }

    private static boolean getExpectedBooleanValue(Cell cell) {
        if (cell == null) {
            return false; // Default value if cell is null
        }
        switch (cell.getCellType()) {
            case STRING:
                String value = cell.getStringCellValue().trim().toUpperCase();
                return value.equals("TRUE"); // Return true only if the string is "TRUE"
            case BOOLEAN:
                return cell.getBooleanCellValue(); // Directly return boolean value
            default:
                return false; // Default value if the cell type is not supported
        }
    }
}
