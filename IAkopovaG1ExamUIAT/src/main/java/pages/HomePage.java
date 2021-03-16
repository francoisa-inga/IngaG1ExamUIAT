package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.fail;

public class HomePage extends ParentPage {


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @FindBy(xpath=".//*[@data-id='c11e1188-07a2-4133-8ff3-a57c49924134']")
    protected WebElement countriesDropDownList;

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

}
