package SeleniumAutomation;

import SeleniumAutomation.Pages.GoogleHomePage;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {

    @Test
    public void testGoogle(){
        driver.get("http://www.google.com");
        GoogleHomePage googleHomePage = new GoogleHomePage(driver, wait);
        googleHomePage.search("Selenium");
        System.out.println("a");
    }
    @Test
    public void testGoogle2(){
        driver.get("http://www.google.com");
        GoogleHomePage googleHomePage = new GoogleHomePage(driver, wait);
        googleHomePage.search("Selenium");
        System.out.println("a");
    }
}
