package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;

public class JordanPage extends ParentPage {


    public JordanPage(WebDriver webDriver) {
        super(webDriver);
    }



    @FindBy(xpath=".//*[contains(text(),'Йорданія міні')]")
    WebElement jordanMiniTour;


    @Override
    String getRelativeUrl() {
        return "/country/jordan";
    }

    public JordanPage waitForJordanPageToLoad() {
        getWebDriverWait20.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//*[@class='page-header']/descendant::*")));

        return new JordanPage(webDriver);
    }


    public JordanPage checkIsRedirectedToJordanPage() {

        Assert.assertEquals("Invalid Jordan page",
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl());
        return this;
    }


}
