package tests;

import org.testng.annotations.DataProvider;

public class Test extends BaseTest{

    // login -> testUser, test@123
    //          admin , admin

    @org.testng.annotations.Test(dataProvider = "provider")
    public void test1(String userName, String password) throws Exception {
        driver.get("https://www.google.com");
        System.out.println(userName);
        System.out.println(password);
        throw new Exception();
    }

    @DataProvider
    public Object[][] provider() {
        Object[][] obj = {
                {"testUser", "test@123"},
        };
        return obj;
    }

    // excelUtils ->

//    public Object[][] getSheetData(String sheetName){
//        return
//    }


}
