package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ButtonPage {
    private WebDriver chromeDriver;

    public ButtonPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    @FindBy(id = "j_idt88:j_idt98")
    public WebElement dimensionEle;

    @FindBy(id = "j_idt88:j_idt102:imageBtn")
    public WebElement imageBtn;

    @FindBy(id = "j_idt88:j_idt107")
    public WebElement clickBtn;

    @FindBy(xpath = "(//div[@class=\"card\"])[7]//button")
    public List<WebElement> groupOfBtns;




}
