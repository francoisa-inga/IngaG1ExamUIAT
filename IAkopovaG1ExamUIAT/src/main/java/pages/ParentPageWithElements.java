package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public abstract class ParentPageWithElements extends ParentPage{


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

    // Close Map Icon
    @FindBy(xpath=".//*[@class='close ui-dialog-titlebar-close']")
    WebElement closeMapIcon;


    public ParentPageWithElements(WebDriver webDriver) {
        super(webDriver);
    }

    public ParentPageWithElements waitForFastTourOrderFormToLoad() {
        getWebDriverWait20.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//*[@class='modal-title ui-dialog-title']")));

        return this;
    }

    public ParentPageWithElements fillFastTourOrderName(String name){
        clearElement(fastTourOrderName);
        insertTextIntoElement(fastTourOrderName, name);
        return this;
    }

    public ParentPageWithElements fillFastTourOrderPhoneNumber(String phoneNumber) {
        clearElement(fastTourOrderPhoneNumber);
        insertTextIntoElement(fastTourOrderPhoneNumber,phoneNumber);
        return this;
    }

    public ParentPageWithElements fillFastTourOrderEmail(String eMail) {
        clearElement(fastTourOrderEmail);
        insertTextIntoElement(fastTourOrderEmail, eMail);
        return this;
    }

    public ParentPageWithElements fillFastTourOrderCity(String city) {
        clearElement(fastTourOrderCity);
        insertTextIntoElement(fastTourOrderCity, city);
        return this;
    }

    public ParentPageWithElements fillFastTourOrderNotes(String notesText) {
        clearElement(fastTourOrderNotes);
        insertTextIntoElement(fastTourOrderNotes, notesText);

        return this;
    }

    public void clickFastTourOrderSubmitButton() {
        clickElement(fastTourOrderSubmitButton);
    }

    public ParentPageWithElements checkFastTourOrderFormDisplayed() {
        try {
            Assert.assertTrue("Quick order form is not displayed"
                    , isElementDisplayed(fastTourOrderSubmitButton));
        } catch (Exception e) {
            logger.info("exception when submitting fast order form" + e);
        }
        return this;
    }

    public void clickFastTourOrderButton() {
        clickElement(fastTourOrder);
    }

    public void checkFastOrderTourOrderFormHidden() {
        try {
            Assert.assertFalse("Quick order form is displayed while should not be"
                    , isElementDisplayed(fastOrderFormTitle));
        } catch (Exception e) {
            logger.info("exception when submitting fast order form" + e);
        }
    }

    public ParentPageWithElements checkIfRightPageOpen(WebElement webElement){
        try {
            Assert.assertTrue("Wrong page displayed", isElementDisplayed(webElement));
            logger.info("Proper page is on the screen");
        }
        catch(Exception e) {
            logger.error("wrong page on the screen " + e);
        }
    return this;
    }

    public void closeMap(){
        clickElement(closeMapIcon);
    }

}
