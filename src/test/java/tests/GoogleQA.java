package tests;

import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ClickUtils;
import utilities.Constants;
import utilities.TestDataJsonReader;
import utilities.TestDataReader;

import java.time.Duration;
import java.util.*;

public class GoogleQA extends BaseTest {

    @Test
    public void test1() {
        driver.get("https://www.google.com");
        // locators -> using the attributes -> id
        By by = By.xpath("//a[@class='gb_I']");
        WebElement element = driver.findElement(by);
        element.click();
//        element.sendKeys("mobiles");
//        element.sendKeys(Keys.TAB);
//        //assertion -> comparing the expected result with actual result
//        String results = chromeDriver.getPageSource(); // sdkjfsdkjfhkdsjf mobiles
//        boolean result = results.contains("mobiles");
//        Assert.assertEquals(result, true);
    }

    @Test
    public void test2() {
        driver.get("https://en.wikipedia.org/wiki/Artificial_intelligence#History");
//        firefoxDriver.navigate().to("https://www.google.com");
//        By by = By.xpath("//a[@href='#History']");
        String text = driver.findElement(By.xpath("//a[@href='#History']")).getAttribute("class");
        System.out.println(text);
    }

    @Test
    public void test3() {
        driver.get("https://www.amazon.in/");
//        By by = By.xpath("//div[@id=\"nav-xshop\"]//a[contains(@class,\"nav-a\")]");
        List<WebElement> tabs = driver
                .findElements(By.xpath("//div[@id=\"nav-xshop\"]//a[contains(@class,\"nav-a\")]"));
        System.out.println(tabs.size());
    }

    @Test
    public void textBox() {
        Map<String, String> data = TestDataReader.getData("TEXTBOX_DATA", "textBox");
        JSONObject dataJson = TestDataJsonReader.getTestData("TEXTBOX_DATA", "textBox");
        driver.get(url + Constants.INPUT_URL);
        String status = elementUtils.getAttribute(textBoxPage.retrieveEle, "value");
        elementUtils.sendKeys(textBoxPage.retrieveEle, "value");
//        Assert.assertEquals(status, data.get("Status"));
        Assert.assertEquals(status, dataJson.getString("Status"));
        elementUtils.sendKeys(textBoxPage.enterTextBoxEle, Keys.ENTER);
        String errorMessage = elementUtils.getText(textBoxPage.errorTextEle);
//        Assert.assertEquals(errorMessage, data.get("Error_message"));
        Assert.assertEquals(errorMessage, dataJson.getString("Error_message"));
        Point initialPoint = elementUtils.getLocation(textBoxPage.initialPointEle);
        clickUtils.click(textBoxPage.inputEle);
        Point finalPoint = elementUtils.getLocation(textBoxPage.initialPointEle);
        Assert.assertNotEquals(initialPoint.getX(), finalPoint.getX());
        Assert.assertNotEquals(initialPoint.getY(), finalPoint.getY());
    }

    @Test
    public void button() throws InterruptedException {
        driver.get(url + Constants.BUTTON_URL);
        Dimension dimension = buttonPage.dimensionEle.getSize();
        Assert.assertEquals(dimension.getHeight(), 34);
        Assert.assertEquals(dimension.getWidth(), 87);
        buttonPage.imageBtn.click();
        //wait
        Thread.sleep(2000);
        buttonPage.imageBtn.click();
        //wait
        Thread.sleep(2000);
        buttonPage.clickBtn.click();
        System.out.println(buttonPage.groupOfBtns.size());
        Assert.assertEquals(buttonPage.groupOfBtns.size(), 4);
    }

    @Test
    public void dropDown() throws InterruptedException {
        driver.get("https://www.leafground.com/select.xhtml");
        WebElement dropDownEle = driver
                .findElement(By.xpath("//select[@class=\"ui-selectonemenu\"]"));
        Select dropDown1 = new Select(dropDownEle);
        dropDown1.selectByVisibleText("Cypress");
        List<WebElement> options = dropDown1.getOptions();
        for (WebElement element : options) {
            System.out.println(element.getText());
        }

        driver.findElement(By.id("j_idt87:country")).click();
        driver.findElement(By.id("j_idt87:country_3")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("j_idt87:city")).click();
        Thread.sleep(2000);
        //ul[@id="j_idt87:city_items"]//li ----- //li[contains(@id,"j_idt87:city_")]
        List<WebElement> cities = driver
                .findElements(By.xpath("//ul[@id=\"j_idt87:city_items\"]//li"));
        List<String> expectedCities = Arrays.asList("Bengaluru", "Chennai", "Delhi");
        for (int i = 1; i < cities.size(); i++) {
            Assert.assertEquals(cities.get(i).getText(), expectedCities.get(i - 1));
        }
    }


    @Test
    public void test5() {
        driver.get("https://www.leafground.com/select.xhtml");
        WebElement element = driver.findElement(By.id("j_idt87:country"));
        driver.navigate().refresh();
        element = driver.findElement(By.id("j_idt87:country"));
        element.click();

    }

    @Test
    public void checkBox() throws InterruptedException {
        driver.get("https://www.leafground.com/checkbox.xhtml");
        List<WebElement> checkBoxes = driver.findElements(
                By.xpath("//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']"));
        checkBoxes.get(1).click();
        Thread.sleep(2000);
        String str = driver.findElement(By.xpath("//div[@class=\"ui-growl-message\"]")).getText();
        Assert.assertEquals(str, "Checked");
        checkBoxes.get(1).click();
        Thread.sleep(2000);
        str = driver.findElement(By.xpath("//div[@class=\"ui-growl-message\"]")).getText();
        Assert.assertEquals(str, "Unchecked");

        String favLang = "Javascript";
        //table[@id="j_idt87:basic"]//label

        List<WebElement> langCheckBoxes = driver.findElements(By.xpath("//table[@id=\"j_idt87:basic\"]//div[@class=\"ui-chkbox-box ui-widget ui-corner-all ui-state-default\"]"));
        List<WebElement> labels = driver
                .findElements(By.xpath("//label[contains(@for,\"j_idt87:basic\")]"));


        for (int i = 0; i < labels.size(); i++) {
            String text = labels.get(i).getText();
            if (text.equals(favLang)) {
                langCheckBoxes.get(i).click();
                break;
            }
        }


    }

    @Test
    public void radioButton() {
        driver.get("https://www.leafground.com/radio.xhtml");
        List<WebElement> radioButtons = driver.findElements(By.xpath("//table[@id=\"j_idt87:console2\"]//div[contains(@class,\"ui-radiobutton-box\")]"));
        List<WebElement> labels = driver.findElements(By.xpath("//table[@id=\"j_idt87:console2\"]//label"));

        for (int i = 0; i < radioButtons.size(); i++) {
            boolean selected = radioButtons.get(i).isSelected();
            if (selected == true) {
                System.out.println(labels.get(i).getText());
            }
        }

        int age = 55;
        List<WebElement> ageLabels = driver.findElements(By.xpath("//label[contains(@for,\"j_idt87:age:\")]"));
        List<WebElement> ageRadioButtons = driver.findElements(By.xpath("//div[@id=\"j_idt87:age\"]//div[contains(@class,\"ui-radiobutton-box ui-widget ui-corner-all\")]"));

        for (int i = 0; i < ageLabels.size(); i++) {
            String ageGroup = ageLabels.get(i).getText(); // 21-40 Years
            ageGroup = ageGroup.replace(" Years", ""); // 21-40
            String[] ages = ageGroup.split("-"); // 21 40
            int minAge = Integer.parseInt(ages[0]);
            int maxAge = Integer.parseInt(ages[1]);
            if (age >= minAge && age <= maxAge) {
                ageRadioButtons.get(i).click();
            }
        }
    }


    @Test
    public void waits() throws InterruptedException {
        driver.get("https://www.leafground.com/waits.xhtml");
        driver.findElement(By.id("j_idt87:j_idt89")).click();
//        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(20));

        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_idt87:j_idt90")));
//
//        boolean display = chromeDriver.findElement(By.id("j_idt87:j_idt90")).isDisplayed();
//        System.out.println(display);
//        chromeDriver.findElement(By.id("j_idt87:j_idt92")).click();
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt87:j_idt93")));
        driver.findElement(By.id("j_idt87:j_idt95")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class=\"ui-growl-message\"]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("j_idt87:j_idt96")));
        driver.findElement(By.id("j_idt87:j_idt96")).click();
    }

    @Test
    public void table() throws InterruptedException {
        driver.get("https://www.leafground.com/table.xhtml");
        driver.findElement(By.id("form:j_idt89:globalFilter")).sendKeys("India");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));// 0-20
        wait.until(ExpectedConditions.stalenessOf(driver
                .findElement(By.xpath("//tbody[@id=\"form:j_idt89_data\"]//tr"))));


        Thread.sleep(2000);

        List<WebElement> rows = driver
                .findElements(By.xpath("//tbody[@id=\"form:j_idt89_data\"]//tr"));
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (WebElement col : cols) {
                System.out.print(col.getText() + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void file() {
        driver.get("https://www.leafground.com/file.xhtml");
//        driver.findElement(By.id("j_idt88:j_idt89_input")).click();
//        driver.findElement(By.id("j_idt88:j_idt89_input")).sendKeys("/Users/preethir/Downloads/RestAssured.xlsx");
        clickUtils.clickByMouse(driver.findElement(By.id("j_idt88:j_idt89_input")));

    }

    @Test
    public void mouseActions() {
//        chromeDriver.get("https://www.leafground.com/drag.xhtml");
//        WebElement element = chromeDriver.findElement(By.xpath("//div[@id='form:conpnl']"));
        Actions actions = new Actions(driver);
//        actions.dragAndDropBy(element, 100, 200).perform();
//        WebElement source = chromeDriver.findElement(By.id("form:drag_content"));
//        WebElement target = chromeDriver.findElement(By.id("form:drop"));
//        actions.dragAndDrop(source,target).perform();
//        WebElement header = chromeDriver.findElement(By.xpath("//div[@class=\"card\"]//h4"));
//        actions.contextClick(header).perform();
        driver.get("https://www.snapdeal.com/");
        WebElement signIn = driver.findElement(By.cssSelector("div[class=\"accountInner\"]"));
        actions.moveToElement(signIn).keyUp(Keys.CONTROL).perform();
        boolean display = driver.findElement(By.xpath("//a[@href=\"https://www.snapdeal.com/myorders\"]")).isDisplayed();
        Assert.assertTrue(display);
    }

    @Test
    public void alerts() {
        driver.get("https://www.leafground.com/alert.xhtml");
        driver.findElement(By.id("j_idt88:j_idt93")).click();
        driver.switchTo().alert().accept();
//        chromeDriver.switchTo().alert().dismiss();
        switchAlert();
    }

    public void switchAlert() {
        String result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, "User Clicked : OK");
        driver.findElement(By.id("j_idt88:j_idt104")).click();
        driver.switchTo().alert().sendKeys("hello world");
        driver.switchTo().alert().accept();
    }

    @Test
    public void frames() {
        driver.get("https://www.leafground.com/frame.xhtml");
        WebElement iframe = driver.findElement(By.xpath("//iframe[@src=\"default.xhtml\"]"));
        driver.switchTo().frame(iframe);
        driver.findElement(By.id("Click")).click();
        driver.switchTo().parentFrame();
        String str = driver.findElement(By.xpath("//form[@id=\"j_idt88\"]//h5")).getText();
        System.out.println(str);
        WebElement childFrame = driver.findElement(By.xpath("//iframe[@src=\"page.xhtml\"]"));
        driver.switchTo().frame(childFrame);
        WebElement grandChildFrame = driver.findElement(By.xpath("//iframe[@src=\"framebutton.xhtml\"]"));
        driver.switchTo().frame(grandChildFrame);
        driver.findElement(By.id("Click")).click();
        driver.switchTo().parentFrame();
        driver.switchTo().parentFrame();
        str = driver.findElement(By.xpath("//form[@id=\"j_idt88\"]//h5")).getText();
        System.out.println(str);
    }

    @Test
    public void windows() {
        driver.get("https://www.leafground.com/window.xhtml");
        driver.findElement(By.id("j_idt88:new")).click();
        Set<String> windows = driver.getWindowHandles();
        List<String> windowList = new ArrayList<>(windows);
        driver.switchTo().window(windowList.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.leafground.com/dashboard.xhtml");
//        chromeDriver.switchTo().defaultContent();
        driver.close();
        driver.switchTo().window(windowList.get(0));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.leafground.com/window.xhtml");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));


//        JavascriptExecutor javascriptExecutor = chromeDriver;
//        List<WebElement> list = chromeDriver.findElements(By.xpath("//div[contains(@id,\"ember\") and @role=\"region\"]"));
//
//
//        String post = "Happy BirthDay John";
//        String friendName = "Peter";
//
//        boolean flag = false;
//        WebElement noMorePost = chromeDriver.findElement(By.xpath("//a"));
//
//
//        javascriptExecutor.executeScript("arguments[0].click();", noMorePost);
//
//        while (flag == false && noMorePost.isDisplayed() == false) {
//            javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//            list = chromeDriver.findElements(By.xpath("//div[contains(@id,\"ember\") and @role=\"region\"]"));
//            for (WebElement ele : list) {
//                if (ele.getText().contains(post) && ele.getText().contains(friendName)) {
//                    System.out.println("found the post");
//                    flag = true;
//                    break;
//                }
//            }
//        }
    }


    @Test
    public void test(){
        driver.get("https://demoqa.com/automation-practice-form");
        driver.findElement(By.id("uploadPicture")).click();
    }
}


