package SeleniumAutomation.Interfaces;

import SeleniumAutomation.Pages.DetailsPage;
import SeleniumAutomation.Pages.HomePage;
import SeleniumAutomation.Pages.LoginPage;
import SeleniumAutomation.Pages.ProfilePage;

public interface HeaderInterface {


    default LoginPage clickLogout(){
        HeaderInterfaceImpl headerInterfaceImpl = new HeaderInterfaceImpl();
        return HeaderInterfaceImpl.clickLogout();
    }

    default HomePage clickHomeLink(){
        HeaderInterfaceImpl headerInterfaceImpl = new HeaderInterfaceImpl();
        return HeaderInterfaceImpl.clickHomeLink();
    }

    default DetailsPage clickDetailsLink(){
        HeaderInterfaceImpl headerInterfaceImpl = new HeaderInterfaceImpl();
        return HeaderInterfaceImpl.clickDetailsLink();
    }

    default ProfilePage clickProfileLink(){
        HeaderInterfaceImpl headerInterfaceImpl = new HeaderInterfaceImpl();
        return HeaderInterfaceImpl.clickProfileLink();
    }

    default String getStatusText(){
        HeaderInterfaceImpl headerInterfaceImpl = new HeaderInterfaceImpl();
        return HeaderInterfaceImpl.getStatusText();
    }
}
