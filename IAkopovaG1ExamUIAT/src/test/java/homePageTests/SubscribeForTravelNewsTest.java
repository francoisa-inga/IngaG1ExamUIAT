package homePageTests;

import baseTest.BaseTest;
import org.junit.Test;

public class SubscribeForTravelNewsTest extends BaseTest {

    @Test
    public void registerForTravelNews(){

        homePage.openMainPage();
        homePage.waitForHomePageToLoad()
                .checkIsRedirectedToHomePage()
                .insertNameToSubscribe("Peter")
                .insertEmailToSubscribe("myMail1@gmail.com")
                .clickSubscribeButton();
        homePage.switchToRegConfirmPage();
        registrationConfirmationPage.waitForRegistrationConfirmationPageToLoad()
                                    .checkIfRegistrationConfirmPageIsOpen()
                                    .clickReturnToHomePageButton();
        registrationConfirmationPage.closeCurrentTab();
        homePage.switchToHomePage();
        homePage.checkIsRedirectedToHomePage();



    }


}
