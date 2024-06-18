package utilities;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestDataJsonReader {
    private static JSONObject testData;

    private TestDataJsonReader() {

    }

    public static void init() {
        if (testData == null) {
            try {
                FileInputStream fileInputStream = new FileInputStream("src/test/resources/Test_Data.json");
                JSONTokener jsonTokener = new JSONTokener(fileInputStream);
                testData = new JSONObject(jsonTokener);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static JSONObject getTestData(String sheetName, String tcName){
        return testData.getJSONObject(sheetName).getJSONObject(tcName);
    }
}
