package SeleniumAutomation.Pages;


import SeleniumAutomation.BasePage;
import org.openqa.selenium.support.PageFactory;

public class DetailsPage extends BasePage{
    public DetailsPage() {
        PageFactory.initElements(driver, this);
    }
}