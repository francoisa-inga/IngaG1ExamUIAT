package pages;

import org.apache.log4j.Logger;
import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.concurrent.TimeUnit;

public abstract class ParentPage {

    @FindBy(xpath = ".//*[contains(text(),'Авторські тури')]")
    private WebElement homePageLink;

    @FindBy(xpath = ".//*[@class='use-ajax fast-button']")
    WebElement fastTourOrder;

    @FindBy(xpath = ".//*[@class='modal-title ui-dialog-title']")
    WebElement fastOrderFormTitle;

    // Elements of Fast Tour Order form
    @FindBy(xpath = ".//form[@action='/ua/form/fast-order']//*[contains(@id, 'name') and @name='name']")
    WebElement fastTourOrderName;

    @FindBy(xpath = ".//form[@action='/ua/form/fast-order']//*[contains(@id, 'telephone') and @name='telephone']")
    TextInput fastTourOrderPhoneNumber;

    @FindBy(xpath = ".//form[@action='/ua/form/fast-order']//*[contains(@id, 'email') and @name='email']")
    TextInput fastTourOrderEmail;

    @FindBy(xpath = ".//form[@action='/ua/form/fast-order']//*[contains(@id, 'city') and @name='city']")
    TextInput fastTourOrderCity;

    @FindBy(xpath = ".//form[@action='/ua/form/fast-order']//*[contains(@id, 'message') and @name='message']")
    TextInput fastTourOrderNotes;

    @FindBy(xpath = ".//*[@class='webform-button--submit button button--primary js-form-submit form-submit btn-primary btn btn-default' and @type='submit']")
    Button fastTourOrderSubmitButton;


    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10, getWebDriverWait15, getWebDriverWait20;

    // creating object of config.properties and create pairs of keys listed there
    public static ConfigProperties configProperties =
            ConfigFactory.create(ConfigProperties.class);
    protected final String baseUrl = configProperties.base_url();


    Logger logger = Logger.getLogger(getClass());

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        //PageFactory.initElements(webDriver, this);
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(webDriver))
                , this);

        webDriverWait10 = new WebDriverWait(webDriver, 10);
        getWebDriverWait15 = new WebDriverWait(webDriver, 15);
        getWebDriverWait20 = new WebDriverWait(webDriver, 20);

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

    public void clickFastTourOrderButton() {
        clickElement(fastTourOrder);
    }

    public ParentPage waitForFastTourOrderFormToLoad() {
        getWebDriverWait20.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//*[@class='modal-title ui-dialog-title']")));

        return this;
    }

    public ParentPage fillFastTourOrderName(String name){
        clearElement(fastTourOrderName);
        insertTextIntoElement(fastTourOrderName, name);
        return this;
    }

    public ParentPage fillFastTourOrderPhoneNumber(String phoneNumber) {
        clearElement(fastTourOrderPhoneNumber);
        insertTextIntoElement(fastTourOrderPhoneNumber,phoneNumber);
        return this;
    }

    public ParentPage fillFastTourOrderEmail(String eMail) {
        clearElement(fastTourOrderEmail);
        insertTextIntoElement(fastTourOrderEmail, eMail);
        return this;
    }

    public ParentPage fillFastTourOrderCity(String city) {
        clearElement(fastTourOrderCity);
        insertTextIntoElement(fastTourOrderCity, city);
        return this;
    }

    public ParentPage fillFastTourOrderNotes(String notesText) {
        clearElement(fastTourOrderNotes);
        insertTextIntoElement(fastTourOrderNotes, notesText);

        return this;
    }

    public void clickFastTourOrderSubmitButton() {
        clickElement(fastTourOrderSubmitButton);
    }

    public ParentPage checkFastTourOrderFormDisplayed() {
        try {
            Assert.assertTrue("Quick order form is not displayed"
                    , isElementDisplayed(fastTourOrderSubmitButton));
        } catch (Exception e) {
            logger.info("exception when submitting fast order form" + e);
        }
        return this;
    }

/**
    public ParentPage checkFastTourOrderFormDisplayed() {
        try {
            Assert.assertTrue("Quick order form is not displayed"
                    , isElementDisplayed(fastOrderFormTitle));
        } catch (Exception e) {
            logger.info("exception when submitting fast order form" + e);
        }
        return this;
    }
*/



    public void checkFastOrderTourOrderFormHidden() {
        try {
            Assert.assertFalse("Quick order form is displayed while shoult not be"
                    , isElementDisplayed(fastOrderFormTitle));
        } catch (Exception e) {
            logger.info("exception when submitting fast order form" + e);
        }
    }

}
