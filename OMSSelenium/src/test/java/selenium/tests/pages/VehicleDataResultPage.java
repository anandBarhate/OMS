package selenium.tests.pages;

import com.google.gson.JsonObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.DriverBase;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created by anandbarhate on 22/10/2017.
 */
public class VehicleDataResultPage extends DriverBase {
    JsonObject verificationData;
    WebDriver driver;
    public static WebDriverWait webdriverWait;


    @FindBy(how = How.XPATH, using = "//*[@class='reg-mark']")
    private WebElement regNumber;

    @FindBy(how = How.XPATH, using = "//*[@id='pr3']/div/ul/li[2]/span[2]/strong")
    private WebElement make;

    @FindBy(how = How.XPATH, using = "//*[@id='pr3']/div/ul/li[3]/span[2]/strong")
    private WebElement colour;



    public VehicleDataResultPage(WebDriver driver) {
        try {
            this.driver = getDriver();
            webdriverWait = new WebDriverWait(driver,5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVerificationData(JsonObject verificationData) {
        this.verificationData = verificationData;
    }

    public void chcekVehicleData(JsonObject verificationData) {
        webdriverWait.until(ExpectedConditions.visibilityOf(regNumber));
        Assert.assertEquals(verificationData.get("make").getAsString(), make.getText());
        Assert.assertEquals(verificationData.get("colour").getAsString(), colour.getText());
    }
}
