package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AllToursPage extends ParentPageWithElements {


    public AllToursPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/all-tours";
    }

    // Filter fields
    @FindBy(xpath=".//*[@id='edit-title' and @data-original-title='Назва туру']")
    WebElement tourTitle;

    @FindBy(xpath=".//*[@name='field_price_in_uah_value[min]']")
    WebElement tourPriceMin;

    @FindBy(xpath=".//*[@name='field_price_in_uah_value[max]']")
    WebElement tourPriceMax;

    @FindBy(xpath=".//*[@type='submit' and @value='Пошук']")
    WebElement searchButton;

    public AllToursPage waitForAllToursPageToLoad() {
        getWebDriverWait30.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//*[@class='page-header' and contains(text(), 'Всі тури')]")));


                //presenceOfElementLocated(By.xpath(".//*[@id='edit-title' and @data-original-title='Назва туру']")));

                //return this;

        return new AllToursPage(webDriver);
    }

    public AllToursPage checkTheSelectedTours(){
        Assert.assertTrue(webDriver.getCurrentUrl().contains(tourPriceMax.getText()));
        logger.info("all tours meeting the criteria are opened");
        return new AllToursPage(webDriver);
    }


    public AllToursPage checkIsRedirectedToAllToursPage() {

        Assert.assertEquals("Invalid All Tours page",
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl());
        return this;
    }

    public AllToursPage setMinimumTourPrice(String price){
        clearElement(tourPriceMin);
        insertTextIntoElement(tourPriceMin, price);
        return this;
    }

    public AllToursPage setMaximumTourPrice(String price){
        clearElement(tourPriceMax);
        insertTextIntoElement(tourPriceMax, price);
        return this;
    }

    public void clickSearchButton(){
    clickElement(searchButton);
    }




}
