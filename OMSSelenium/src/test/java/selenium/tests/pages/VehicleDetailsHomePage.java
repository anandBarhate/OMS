package selenium.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import selenium.DriverBase;
import selenium.tests.Utils.DataUtil;
import selenium.tests.Utils.Helper;

import java.util.concurrent.TimeUnit;

public class VehicleDetailsHomePage extends DriverBase {

    WebDriver driver;
    @FindBy(how = How.XPATH, using = "//*[@id='get-started']/a")
    private WebElement startButton;
    Helper helper = new Helper();

    public VehicleDetailsHomePage(WebDriver driver) {
        try {
            this.driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testVehicleData() throws Exception {


        driver.get("www.gov.uk/get-vehicle-information-from-dvla");

    }


    public void navigateTo(String url) {
        driver.get(url);
    }

    public VehicleRegCheckPage clickStartNowButton() throws Exception{

        helper.clickElement(startButton);

        TimeUnit.SECONDS.sleep(5);
        VehicleRegCheckPage vehicleServicesPage = PageFactory.initElements(driver, VehicleRegCheckPage.class);
        return vehicleServicesPage;
    }
}