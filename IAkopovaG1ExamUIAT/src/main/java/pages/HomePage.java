package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

import static org.junit.Assert.fail;

public class HomePage extends ParentPageWithElements {


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    //@FindBy(xpath=".//*[@data-id='c11e1188-07a2-4133-8ff3-a57c49924134']")
    @FindBy(xpath=".//*[@class='we-mega-menu-li' and @href='/ua/countries' and contains(text(), 'Країни')]")
    protected WebElement countriesDropDownList;

    @FindBy(xpath=".//*[@class='we-mega-menu-li' and @href='/ua/all-tours']")
    protected WebElement toursDropDownList;

    // Tours by installments dropdown value
    @FindBy(xpath=".//*[@class='we-mega-menu-li' and @href='/ua/node/114']")
    protected WebElement toursByInstallments;

    @FindBy(xpath=".//*[@class='we-mega-menu-li' and @href='/ua/faq']")
    protected WebElement touristFAQsDropdownList;

    // Elements for signing to the mail list
    @FindBy(xpath=".//*[@id='mce-FNAME']")
    protected WebElement eMailListName;

    @FindBy(xpath=".//*[@id='mce-EMAIL']")
    protected WebElement eMailListEmail;

    @FindBy(xpath=".//*[@name='subscribe' and @type='submit']")
    protected WebElement subscribeButton;

    // Link to Agencies list
    @FindBy(xpath=".//*[@class='fr-dashed']")
    protected WebElement linkToAgenciesList;

    @FindBy(xpath=".//*[@class='btn btn-primary']")
    protected WebElement allAgenciesButton;



    public void openMainPage(){
            try {
                webDriver.get(baseUrl);
                logger.info("Home Page opened");
            } catch (Exception e) {
                logger.info("can't open Home Page");
                fail("can't open Home Page");
            }

        }


    public HomePage waitForHomePageToLoad() {
        getWebDriverWait20.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//*[@id='block-novyibannera']/descendant::*")));

        return new HomePage(webDriver);
    }

    public HomePage checkIsRedirectedToHomePage() {

        Assert.assertEquals("Invalid page",
                baseUrl,
                webDriver.getCurrentUrl());
        return this;
    }

        public void clickCountriesList(){
        clickElement(countriesDropDownList);
    }

        public void clickToursList(){
            clickElement(toursDropDownList);
            logger.info("Tours list was clicked");
    }

        public HomePage insertNameToSubscribe(String name){
            clearElement(eMailListName);
            insertTextIntoElement(eMailListName, name);
        return this;
        }

        public HomePage insertEmailToSubscribe(String eMail){
            clearElement(eMailListEmail);
            insertTextIntoElement(eMailListEmail, eMail);
        return this;
        }

        public void clickSubscribeButton(){
            subscribeButton.click();
        }

        public void clickOnLinkToAgenciesList(){
            //linkToAgenciesList.click();
            clickElement(linkToAgenciesList);
            logger.info("opening list of travel agencies");
        }

        public HomePage waitForAgenciesListPopup(){
            getWebDriverWait20.until(ExpectedConditions
                    .visibilityOf(allAgenciesButton));
                    //.visibilityOfElementLocated(By.xpath(".//*[@id='block-novyibannera']/descendant::*")));


            return this;
        }

        public void clickAllAgenciesButton() {
            try {
                clickElement(allAgenciesButton);
                logger.info("All Agencies button clicked");

            } catch (Exception e) {
                logger.error("error opening list of agencies");
            }
        }

    public HomePage selectToursByInstallmentsInDropDown(String dropDownValueText) {
        //Find dropdown
        moveToElement(touristFAQsDropdownList);
        //clickElement(touristFAQsDropdownList);
        //selectValueInDropDown(toursDropDownList, dropDownValueText);
        clickElement(toursByInstallments);
        logger.info("Tours by installments selected");
        return this;
    }

    public void switchToRegConfirmPage(){
        ArrayList tabsFeerie = new ArrayList(webDriver.getWindowHandles());
        webDriver.switchTo().window(String.valueOf(tabsFeerie.get(1)));
    }

    public void switchToHomePage(){
        ArrayList tabsFeerie = new ArrayList(webDriver.getWindowHandles());
        webDriver.switchTo().window(String.valueOf(tabsFeerie.get(0)));
    }


}
