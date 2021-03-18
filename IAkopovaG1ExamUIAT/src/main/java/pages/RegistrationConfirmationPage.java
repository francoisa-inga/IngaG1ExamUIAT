package pages;


import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class RegistrationConfirmationPage extends ParentPageWithElements{


    // creating object of config.properties and create pairs of keys listed there

    protected final String baseSibscribeUrl = configProperties.base_subscribe_url();

    public RegistrationConfirmationPage(WebDriver webDriver) {
        super(webDriver);
    }

    String getRelativeSubscribeUrl(){
        return "/subscribe/post?u=c90b751c02493e97c1941dcf6&id=d6d637759d";

    };

    @FindBy(xpath=".//*[@id='templateBody']")
    WebElement mailConfirmationFormBody;

    @FindBy(xpath=".//*[@class='formEmailButton']")
    WebElement returnToHomePageButton;


    @Override
    String getRelativeUrl() {
        return null;
    }

    public RegistrationConfirmationPage waitForRegistrationConfirmationPageToLoad() {
        getWebDriverWait20.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//*[@id='templateBody']")));

        return new RegistrationConfirmationPage(webDriver);
    }

    public RegistrationConfirmationPage checkIfRegistrationConfirmPageIsOpen(){
        Assert.assertEquals("Invalid Registration confirmation page",
                baseSibscribeUrl + getRelativeSubscribeUrl(),
                webDriver.getCurrentUrl());
        return this;
    }

    public void clickReturnToHomePageButton(){
        clickElement(returnToHomePageButton);
        logger.info("Returning to homepage");
    }

}
