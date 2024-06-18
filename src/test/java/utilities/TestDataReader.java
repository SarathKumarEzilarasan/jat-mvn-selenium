package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestDataReader {
    private static XSSFWorkbook workbook;
    private static Map<String, Map<String, Map<String, String>>> testData;


    private TestDataReader() {

    }

    public static void init() {
        if (testData == null) {
            testData = new HashMap<>();
            try {
                FileInputStream fileInputStream = new FileInputStream("src/test/resources/Test_Data.xlsx");
                workbook = new XSSFWorkbook(fileInputStream);
                for (int k = 0; k < workbook.getNumberOfSheets(); k++) {
                    Sheet sheet = workbook.getSheetAt(k);
                    Row headers = sheet.getRow(0);
                    Map<String, Map<String, String>> rows = new HashMap<>();
                    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                        Row row = sheet.getRow(i);
                        Map<String, String> cols = new HashMap<>();
                        for (int j = 1; j < row.getLastCellNum(); j++) {
                            Cell cell = row.getCell(j);
                            cols.put(headers.getCell(j).getStringCellValue(), cell.getStringCellValue());
                        }
                        rows.put(row.getCell(0).getStringCellValue(), cols);
                    }
                    testData.put(sheet.getSheetName(), rows);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static Map<String, String> getData(String sheetName, String tcName) {
        return testData.get(sheetName).get(tcName);
    }
}


// Map<Column_name,Column_value>

// Map<TC_Name, Map<Column_name,Column_value> > ???

//  Map<Sheet_Name, Map<TC_Name, Map<Column_name,Column_value> >>