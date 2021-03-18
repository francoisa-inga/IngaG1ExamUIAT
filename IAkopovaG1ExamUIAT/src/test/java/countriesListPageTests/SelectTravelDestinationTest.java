package countriesListPageTests;

import baseTest.BaseTest;
import org.junit.Test;

public class SelectTravelDestinationTest extends BaseTest {

@Test
public void selectDesiredTour() {

    homePage.openMainPage();
    homePage.clickCountriesList();
    countriesListPage.checkIsRedirectedToCountriesListPage();
    countriesListPage.clickCountryInputField();
    countriesListPage.clearSearchField();
    countriesListPage.insertTextIntoSearchField("Йорданія")
            .clickCountriesListPageHeader();
    countriesListPage.clickSearchButton();
    jordanPage.waitForJordanPageToLoad()
            .checkIfRedirectedToJordanPage()
            .clickFastTourOrderButton();
    jordanPage.checkFastTourOrderFormDisplayed()
              .fillFastTourOrderName("Bill Gates")
              .fillFastTourOrderPhoneNumber("+1209876534")
              .fillFastTourOrderEmail("myMail@gmail.com")
              .fillFastTourOrderCity("NY")
              .fillFastTourOrderNotes("A.s.a.p.");
    // I'll make it as a comment because when I tested it, I managed to send a request with fake data
             // .clickFastTourOrderSubmitButton();

}
}
