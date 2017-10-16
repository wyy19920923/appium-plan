package com.appium.pages;

import com.appium.util.BaseUtils;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by wangyuying on 2017/8/27.
 */
public class HealthPage extends BasePage {

//    Constructor
    public HealthPage(AndroidDriver driver) {
        super(driver);
    }

    // 中医治未病
    @FindBy(id = "rl_health_func21")
    private WebElement measurementButton;

    public void gotoMeasurementTest() {
        BaseUtils.waitForElement(driverWait, measurementButton).click();
    }

}
