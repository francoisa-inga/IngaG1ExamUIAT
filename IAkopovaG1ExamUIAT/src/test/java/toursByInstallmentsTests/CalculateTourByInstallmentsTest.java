package toursByInstallmentsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CalculateTourByInstallmentsTest extends BaseTest {

    @Test
    public void calculateTourPaymentByInstallments(){

        homePage.openMainPage();
        homePage.waitForHomePageToLoad()
                .checkIsRedirectedToHomePage()
                .selectToursByInstallmentsInDropDown("Тури в розстрочку");
        toursByInstallmentsPage.waitForTourByInstallmentPageToLoad();
        toursByInstallmentsPage.checkIfRedirectedToToursByInstallmentsPage()
                                .insertTourPrice("5000")
                                .selectСalculationMode()
                                .checkTheProperInstallments4();

    }


}
