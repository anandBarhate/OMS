package selenium.tests.pages;

import com.google.gson.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.DriverBase;
import selenium.tests.Utils.DataUtil;
import selenium.tests.Utils.Helper;


import java.util.concurrent.TimeUnit;

/**
 * Created by anandbarhate on 22/10/2017.
 */
public class VehicleRegCheckPage extends DriverBase {

    @FindBy(how = How.XPATH, using = "//*[@id='Vrm']")
    private WebElement registrationTextElement;

    @FindBy(how = How.XPATH, using = "//*[@name='Continue']")
    private WebElement continueButton;

    DataUtil dataUtil = new DataUtil();
    WebDriver driver;
    Helper helper = new Helper();



    public static WebDriverWait webdriverWait;
    public VehicleRegCheckPage(WebDriver driver) {
        try {
            this.driver = getDriver();
            webdriverWait = new WebDriverWait(driver, 15);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void vehicleDataTest() {
        try {
            JsonArray jsonElements = dataUtil.retrieveVehicleData();
            jsonElements.forEach(
                    element -> {
                        JsonObject jobject = element.getAsJsonObject();
                        //System.out.println(jobject.get("regNumber"));
                        submitVehicleReg(jobject);
                    }
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void submitVehicleReg(JsonObject jobject) {
        String vehicleRegNumber = jobject.get("regNumber").getAsString();
        webdriverWait.until(ExpectedConditions.visibilityOf(registrationTextElement));
        webdriverWait.until(ExpectedConditions.visibilityOf(continueButton));
        helper.clickElement(registrationTextElement);
        registrationTextElement.sendKeys(vehicleRegNumber);
        helper.clickElement(continueButton);

        VehicleDataResultPage vehicleResultsPage = PageFactory.initElements(driver,VehicleDataResultPage.class);
        vehicleResultsPage.setVerificationData(jobject);
        vehicleResultsPage.chcekVehicleData(jobject);

    }
}
