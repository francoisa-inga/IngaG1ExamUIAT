package homePageTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class OpenHomePageTest extends BaseTest {

    @Test
    public void openHomePage() {

            homePage.openMainPage();
    }

    @Test
    public void clickListOfCountries() {

        homePage.openMainPage();
        homePage.clickCountriesList();
    }



}
