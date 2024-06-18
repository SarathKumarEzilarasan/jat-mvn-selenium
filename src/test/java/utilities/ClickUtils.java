package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ClickUtils {
    // element.click()
    // actions.moveToElement().click()
    // JavaScriptExecutor

    private WebDriver driver;
    private WaitUtils waitUtils;

    public ClickUtils(WebDriver driver) {
        this.driver = driver;
        waitUtils = new WaitUtils(driver);
    }

    public void click(WebElement element) {
        waitUtils.waitForVisibility(element);
        element.click();
    }

    public void clickByMouse(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public void clickByJS(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click()", element);
    }

}
