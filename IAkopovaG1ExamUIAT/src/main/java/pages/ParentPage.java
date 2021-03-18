package pages;

import org.apache.log4j.Logger;
import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.sql.DriverManager.getDriver;

public abstract class ParentPage {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10, getWebDriverWait15, getWebDriverWait20, getWebDriverWait30;



    // creating object of config.properties and create pairs of keys listed there
    public static ConfigProperties configProperties =
            ConfigFactory.create(ConfigProperties.class);
    protected final String baseUrl = configProperties.base_url();


    Logger logger = Logger.getLogger(getClass());

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(webDriver))
                , this);

        webDriverWait10 = new WebDriverWait(webDriver, 10);
        getWebDriverWait15 = new WebDriverWait(webDriver, 15);
        getWebDriverWait20 = new WebDriverWait(webDriver, 20);
        getWebDriverWait30 = new WebDriverWait(webDriver, 30);

    }

    abstract String getRelativeUrl();

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if (webElement instanceof TypifiedElement) {
            elementName = " '" + ((TypifiedElement) webElement).getName() + "' ";
        }
        return elementName;

    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            logger.info(getElementName(webElement) + "Element displayed : " + state);
            return state;
        } catch (Exception e) {
            logger.info(getElementName(webElement) + "Element is not yet displayed : false");
            return false;
        }
    }

    protected void checkIsElementVisible(WebElement webElement){
        Assert.assertTrue( "element is not visible", isElementDisplayed(webElement));

    }

    private void printErrorMessageAndStopTest(Exception e) {
        logger.error("Can't work with element " + e);
        Assert.fail("Can't work with element " + e);
    }

    public void clickElement(WebElement webElement) {
        try {
            getWebDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement) + "Element was clicked");
        } catch (Exception e) {
            printErrorMessageAndStopTest(e);
        }
    }

    public ParentPage clearElement(WebElement webElement) {
        webElement.clear();
        return this;
    }


    public void insertTextIntoElement(WebElement webElement, String text) {
        webElement.sendKeys(text);
    }


    protected void selectValueInDropDown(WebElement webElement, String value) {
        try {
            Select select = new Select(webElement);
            // Here webElement is closed dropdown
            select.selectByValue(value);
            logger.info(value + "was selected in dropdown" + getElementName(webElement));
        } catch (Exception e) {
            printErrorMessageAndStopTest(e);
        }
    }

    public void moveToElement(WebElement webElement){
        Actions act = new Actions(webDriver);
            act.moveToElement(webElement).perform();
        }

    public void closeCurrentTab(){
        ArrayList tabsFeerie = new ArrayList(webDriver.getWindowHandles());
        webDriver.close();
    }

}
