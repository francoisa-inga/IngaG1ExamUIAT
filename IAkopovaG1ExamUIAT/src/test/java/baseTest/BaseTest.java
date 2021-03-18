package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.*;

import java.util.concurrent.TimeUnit;

import static pages.ParentPage.configProperties;


public class BaseTest {

    public WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected HomePage homePage;
    protected CountriesListPage countriesListPage;
    protected JordanPage jordanPage;
    protected TravelPage travelPage;
    protected AllToursPage allToursPage;
    protected RegistrationConfirmationPage registrationConfirmationPage;
    protected AgenciesListPage agenciesListPage;
    protected ToursByInstallmentsPage toursByInstallmentsPage;

    @Before
    public void setUo() throws Exception{
        logger.info("test started");
        webDriver = initDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(configProperties.TIME_FOR_DFFAULT_WAIT(), TimeUnit.SECONDS);
        logger.info("Browser Chrome opened");

        // Declaring pages

        homePage = new HomePage(webDriver);
        countriesListPage = new CountriesListPage(webDriver);
        travelPage = new TravelPage(webDriver);
        jordanPage = new JordanPage(webDriver);
        allToursPage = new AllToursPage(webDriver);
        registrationConfirmationPage = new RegistrationConfirmationPage(webDriver);
        agenciesListPage = new AgenciesListPage(webDriver);
        toursByInstallmentsPage = new ToursByInstallmentsPage(webDriver);

    }


    private WebDriver initDriver() throws Exception {
        // Switch between varios web drivers
        String browser = System.getProperty("browser");

        if ((browser == null) || ("chrome".equalsIgnoreCase(browser))) {
            // Create new instance of browser
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }


        else {
            throw new Exception("check browser variable");
        }
    }

    @After
    public void testDown(){
        webDriver.quit();
        logger.info("Browser is closed");
        logger.info("test ended");
    }



}
