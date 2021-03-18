package homePageTests;

import baseTest.BaseTest;
import org.junit.Test;

public class FindFilteredToursTest extends BaseTest {

    @Test
    public void findSuitableTourByFilters(){

        homePage.openMainPage();
        homePage.clickToursList();
        allToursPage.waitForAllToursPageToLoad();
        allToursPage.checkIsRedirectedToAllToursPage()
                    .setMinimumTourPrice("100")
                    .setMaximumTourPrice("1000")
                    .clickSearchButton();
        allToursPage.checkTheSelectedTours();



    }
}
