package pages;

import org.openqa.selenium.WebDriver;

public class TravelPage extends ParentPageWithElements {



    public TravelPage(WebDriver webDriver) {
        super(webDriver);
    }


    @Override
    String getRelativeUrl() {
        return "/";
    }

}
