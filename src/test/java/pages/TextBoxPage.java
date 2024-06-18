package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextBoxPage {
    // Page Class -> Page Object Model [POM] -> Page Factory Model

    private WebDriver chromeDriver;

    public TextBoxPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        PageFactory.initElements(chromeDriver, this);
    }
//
//    public By enterTextBoxBy = By.id("j_idt106:thisform:age");
//    public By errorTextBy = By.id("j_idt106:thisform:j_idt110_error-detail");
//    public By initialPointBy = By.id("j_idt106:j_idt113");
//    public By inputBy = By.id("j_idt106:float-input");
//
////    public By retrieveBy = By.id("j_idt88:j_idt97");
////    public WebElement element = chromeDriver.findElement(retrieveBy);
//

    @FindBy(id = "j_idt88:j_idt97")
    public WebElement retrieveEle;

    @FindBy(id = "j_idt106:thisform:age")
    public WebElement enterTextBoxEle;

    @FindBy(id = "j_idt106:thisform:j_idt110_error-detail")
    public WebElement errorTextEle;

    @FindBy(id = "j_idt106:j_idt113")
    public WebElement initialPointEle;

    @FindBy(id = "j_idt106:float-input")
    public WebElement inputEle;



}
