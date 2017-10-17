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

    // 引导结束页
    @FindBy(id = "done")
    private WebElement finishButton;

    // 搜索框
    @FindBy(id = "tv_search")
    private WebElement searchButton;

    // 首页按钮
    @FindBy(name = "首页")
    private WebElement homePageButton;

    // 健康按钮
    @FindBy(id = "tab_container_1")
    private WebElement healthPageButton;


    //我的主页按钮
    @FindBy(id = "tab_container_3")
    private WebElement myPageButton;


    public void gotoMyPage() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (BaseUtils.waitForElementVisibility(driverWait, myPageButton)){
            BaseUtils.waitForElement(driverWait, myPageButton).click();   //判断“我的”按钮是否存在，存在的话点击
        }else{
            BaseUtils.swipeToLeft(driver,4,3000);  //“我的”按钮不存在，则判断为在首次启动的引导页，进行页面左滑动
            BaseUtils.waitForElement(driverWait, finishButton).click();  //完成引导页滑动
            BaseUtils.waitForElement(driverWait, myPageButton).click();   //点击“我的”按钮
        }
    }

    public void gotoHealthPage() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (BaseUtils.waitForElementVisibility(driverWait, healthPageButton)){
            BaseUtils.waitForElement(driverWait, healthPageButton).click();   //判断“健康”按钮是否存在，存在的话点击
        }else{
            BaseUtils.swipeToLeft(driver,4,3000);  //“健康”按钮不存在，则判断为在首次启动的引导页，进行页面左滑动
            BaseUtils.waitForElement(driverWait, finishButton).click();  //完成引导页滑动
            BaseUtils.waitForElement(driverWait, healthPageButton).click();   //点击“健康”按钮
        }
    }

}
