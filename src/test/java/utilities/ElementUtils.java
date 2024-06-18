package utilities;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils {
    private WebDriver driver;
    private WaitUtils waitUtils;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
        waitUtils = new WaitUtils(driver);
    }

    public void sendKeys(WebElement element, CharSequence value) {
        waitUtils.waitForVisibility(element);
        element.sendKeys(value);
    }

    public String getText(WebElement element) {
        waitUtils.waitForVisibility(element);
        return element.getText();
    }

    public String getAttribute(WebElement element, String value) {
        waitUtils.waitForVisibility(element);
        return element.getAttribute(value);
    }

    public Point getLocation(WebElement element) {
        waitUtils.waitForVisibility(element);
        return element.getLocation();
    }


}
