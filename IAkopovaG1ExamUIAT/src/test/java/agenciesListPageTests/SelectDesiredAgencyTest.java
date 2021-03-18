package agenciesListPageTests;

import baseTest.BaseTest;
import org.junit.Test;

public class SelectDesiredAgencyTest extends BaseTest {

    @Test
    public void selectTravelAgency(){

        homePage.openMainPage();
        homePage.checkIsRedirectedToHomePage()
                .clickOnLinkToAgenciesList();
        homePage.waitForAgenciesListPopup()
                .clickAllAgenciesButton();
        agenciesListPage.waitForAgenciesListPageToLoad()
                        .checkIfRedirectedToAgenciesListPage()
                        .clickAgencisInOtherCities()
                        .checkIfOtherCitiesAgenciesTabClicked()
                        .selectAgencyInIsmail("678");
        agenciesListPage.clickSearchCityButton();
        agenciesListPage.checkIsRightCitySelected()
                        .clickLinkToShowMap()
                        .checkIfMapIsLoaded()
                        .closeMap();
        agenciesListPage.waitUntilMapIsClosed()
                        .checkIfMapIsClosed();


    }
}
