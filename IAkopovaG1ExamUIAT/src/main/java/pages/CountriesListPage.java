package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CountriesListPage extends ParentPageWithElements {

    @FindBy(xpath=".//*[@id='edit-name' and @data-original-title='Название страны/города:']")
    protected TextInput countryInputField;

    @FindBy(xpath=".//*[@id='edit-submit-countries']")
    protected Button submitCountryName;

    @FindBy(xpath=".//*[@class='page-header']")
    WebElement countriesListHeaderCountries;


    @Override
    String getRelativeUrl() {
        return "/countries";
    }

    public CountriesListPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CountriesListPage waitForCountriesListPageToLoad() {
        getWebDriverWait20.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//*[@id='edit-submit-countries']")));

        return new CountriesListPage(webDriver);
    }

    public CountriesListPage checkIsRedirectedToCountriesListPage() {

        Assert.assertEquals("Invalid Countries list page",
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl());
        return this;
    }

    public void clickCountryInputField(){
        clickElement(countryInputField);
    }

    public void clearSearchField(){
        clearElement(countryInputField);
    }

    public CountriesListPage insertTextIntoSearchField(String text){

        insertTextIntoElement(countryInputField, text);
        return this;
    }

    public void clickSearchButton(){
        clickElement(submitCountryName);
    }

    public void clickCountriesListPageHeader(){
        clickElement(countriesListHeaderCountries);
    }


}
