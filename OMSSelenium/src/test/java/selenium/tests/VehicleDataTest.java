package selenium.tests;

import org.openqa.selenium.support.PageFactory;
import selenium.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import selenium.tests.pages.VehicleDetailsHomePage;
import selenium.tests.pages.VehicleRegCheckPage;

import java.util.concurrent.TimeUnit;

public class VehicleDataTest extends DriverBase {




    @Test
    public void testVehicleData() throws Exception {
        VehicleDetailsHomePage homepage = PageFactory.initElements(getDriver(), VehicleDetailsHomePage.class);
        homepage.navigateTo("https://www.gov.uk/get-vehicle-information-from-dvla");
        VehicleRegCheckPage vehicleRegCheckPage = homepage.clickStartNowButton();

        vehicleRegCheckPage.vehicleDataTest();
        TimeUnit.SECONDS.sleep(10);




    }
}