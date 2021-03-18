package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AgenciesListPage extends ParentPageWithElements {
    public AgenciesListPage(WebDriver webDriver) {
        super(webDriver);
    }

    //Agencies in cities outside Kyiv
    @FindBy(xpath=".//*[@href='/ua/quicktabs/nojs/kontaktna_informacia/1']")
    protected WebElement agenciesInOtherCities;

    // Dropdown to select city to pick agency
    @FindBy(xpath=".//*[@class='form-select form-control']")
    protected WebElement citiesSelectionDropdown;

    @FindBy(xpath=".//*[@id='edit-submit-agencies']")
    protected WebElement searchCityButton;

    @FindBy(xpath = ".//*[@class='form-select form-control']//*[@value='678']")
    protected WebElement valueIsmail;

    @FindBy(xpath=".//*[@class='field-content' and contains(text(), ' Феєрія Мандрів, Ізмаїл')]")
    protected WebElement ismTitle;
    
    //Link to open the map
    @FindBy(xpath=".//*[@class='use-ajax fr-show-popover-map no-text']")
    protected WebElement linkToPopUpMap;

    // Pop Up Map
    @FindBy(xpath=".//*[@class='field field--name-field-location field--type-geolocation field--label-hidden field--item']")
    WebElement googlePopUpMap;

    @Override
    String getRelativeUrl() {
        return "/contacts?contact_info";
    }

    public AgenciesListPage waitForAgenciesListPageToLoad() {
        getWebDriverWait20.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//*[@class='page-header']")));

        return new AgenciesListPage(webDriver);
    }

    public AgenciesListPage checkIfRedirectedToAgenciesListPage() {

        Assert.assertEquals("Invalid List of Agencies page",
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl());
        return this;
    }

    public AgenciesListPage clickAgencisInOtherCities(){
        clickElement(agenciesInOtherCities);
        logger.info("selecting agecies outside Kyiv");
    return this;
    }

    public AgenciesListPage checkIfOtherCitiesAgenciesTabClicked(){
        try {
            Assert.assertTrue(citiesSelectionDropdown.isDisplayed());
            logger.info("select city");
        }
        catch (Exception e) {
            logger.error("no cities dropdown, Tab All cities was not properly clicked");
        }
    return this;
    }

    public AgenciesListPage selectAgencyInIsmail(String cityValue){
        clickElement(citiesSelectionDropdown);
        selectValueInDropDown(citiesSelectionDropdown,cityValue);
        clickElement(valueIsmail);
    return this;
    }

    public void clickSearchCityButton(){
        clickElement(searchCityButton);
        logger.info("Search agencies in selected city clicked");
    }

    public AgenciesListPage checkIsRightCitySelected (){
        checkIfRightPageOpen(ismTitle);
    return this;
    }
    
    public AgenciesListPage clickLinkToShowMap(){
        clickElement(linkToPopUpMap);
    return this;
    }

    public AgenciesListPage checkIfMapIsLoaded(){
        try {
            getWebDriverWait20.until(ExpectedConditions
                    .visibilityOf(googlePopUpMap));
            logger.info("Map is loaded");
        }
        catch (Exception e) {
            logger.error("Map loading error" + e);
        }
    return this;
    }

    public AgenciesListPage waitUntilMapIsClosed(){
        getWebDriverWait30.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(".//*[@class='field field--name-field-location field--type-geolocation field--label-hidden field--item']")));
                logger.info("close google map");
               // .invisibilityOf(googlePopUpMap));
    return this;
    }

    public AgenciesListPage checkIfMapIsClosed(){
        try {
            Assert
                .assertTrue("Map is displayed while it should not be", googlePopUpMap.isDisplayed());
            logger.info("Map is closed");
        }
        catch (Exception e) {
            logger.error("Map closing error" + e);
        }
        return this;
    }
}
