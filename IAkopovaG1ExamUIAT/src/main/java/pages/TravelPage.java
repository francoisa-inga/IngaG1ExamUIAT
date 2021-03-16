package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TravelPage extends ParentPage{



    public TravelPage(WebDriver webDriver) {
        super(webDriver);
    }


    @Override
    String getRelativeUrl() {
        return "/";
    }

}
