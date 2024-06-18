package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleQA1 extends BaseTest{

    @Test
    public void test1() {
        driver.get("https://www.google.com");
        By by = By.xpath("//a[@class='gb_I']");
        WebElement element = driver.findElement(by);
        element.click();
    }

    @Test
    public void test2() {
        driver.get("https://www.google.com");
        By by = By.xpath("//a[@class='gb_I']");
        WebElement element = driver.findElement(by);
        element.click();
    }
}


// encapsulation
// inheritance
// polymorphism
// abstraction