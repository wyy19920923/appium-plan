package com.appium.pages;

import com.appium.util.BaseUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by wangyuying on 2017/8/27.
 */
public class MainPage extends BasePage {

//    Constructor
    public MainPage(AndroidDriver  driver) {
        super(driver);
    }

    // 搜索框
    @FindBy(id = "start_search")
    private WebElement searchButton;

    // 首页按钮
    @FindBy(name = "首页")
    private WebElement homePageButton;

    // 就餐人数选择按钮
    @FindBy(name = "发现")
    private WebElement findPageButton;

    // 我的主页按钮
    @FindBy(name = "我的")
    private WebElement myPageButton;

    // 关闭图片按钮（可能出现）
    @FindBy(id = "oprate_cross_icon")
    private WebElement crossIconButton;

    public void skipIcon() {
        //System.out.println("waiting the icon...");
        if (BaseUtils.waitForElementVisibility(driverWait, crossIconButton)) {
            //System.out.println("Find the icon and click");
            crossIconButton.click();
        }
    }

    public void gotoMyPage() {
        BaseUtils.waitForElement(driverWait, myPageButton).click();
    }

    public void checkLoaded() {
        Assert.assertTrue(BaseUtils.waitForElement(
                driverWait, searchButton).getText().equals("输入商户名、地点"));
		/*Assert.assertTrue(BaseUtils.waitForElementPresent(
			driverWait, homePageButton));
		Assert.assertTrue(BaseUtils.waitForElementPresent(
			driverWait, tuanPageButton));
		Assert.assertTrue(BaseUtils.waitForElementPresent(
			driverWait, findPageButton));
		Assert.assertTrue(BaseUtils.waitForElementPresent(
			driverWait, myPageButton));*/
    }
}
