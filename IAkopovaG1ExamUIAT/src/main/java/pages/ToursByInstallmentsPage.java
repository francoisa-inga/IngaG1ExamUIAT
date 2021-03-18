package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ToursByInstallmentsPage extends ParentPageWithElements {
    public ToursByInstallmentsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath=".//*[@id='price-and-type']//*[@type='text']")
    protected WebElement tourPrice;

    @FindBy(xpath=".//*[@id='type-of-credit']")
    protected WebElement tourPaymentModeDropDown;

    @FindBy(xpath=".//*[@id='type-of-credit']//*[contains(text(), 'Чотири платежі')]")
    protected WebElement installmentsDropDownValue;

    @FindBy(xpath=".//*[@class='payments']//*[contains(text(), '4 пл')]")
    protected WebElement installmentsNumber4;

    @Override
    String getRelativeUrl() {
        return "/node/114";
    }

    public ToursByInstallmentsPage insertTourPrice(String price){

        insertTextIntoElement(tourPrice, price);
    return this;
    }

    public ToursByInstallmentsPage waitForTourByInstallmentPageToLoad() {
        getWebDriverWait20.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//*[@class='page-header']/descendant::*")));

        return this;
    }

    public ToursByInstallmentsPage checkIfRedirectedToToursByInstallmentsPage() {

        Assert.assertEquals("Invalid Jordan page",
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl());
        return this;
    }


    public ToursByInstallmentsPage selectСalculationMode(){

            //Find dropdown
            clickElement(tourPaymentModeDropDown);
            clickElement(installmentsDropDownValue);
            clickElement(tourPaymentModeDropDown);

    return this;
    }

    public ToursByInstallmentsPage checkTheProperInstallments4(){
        Assert.assertTrue("Wrong number of installments calculated",
                installmentsNumber4.isDisplayed());

        return this;
    }


}