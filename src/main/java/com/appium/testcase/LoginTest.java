package com.appium.testcase;

import com.appium.pages.MainPage;
import com.appium.pages.MyPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wangyuying on 2017/8/27.
 */
public class LoginTest {

    private static final String APK_HOME = "D:\\intellijworkspace\\appium-plan\\src\\main\\resources\\dazhongdianping.apk";
    public AndroidDriver driver;

    @BeforeTest
    public void setUp() throws Exception{

        //设置appium启动项
        // load apk file
        File apk = new File(APK_HOME);

        DesiredCapabilities capabilities =new  DesiredCapabilities();
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("app", apk.getAbsolutePath());
        capabilities.setCapability("deviceName", "864394010761874");      //模拟器IMEI
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "4.4.2");
        capabilities.setCapability("uuid", "127.0.0.1:62001");    //设备uuid
        capabilities.setCapability("appPackage", "com.dianping.v1");
        capabilities.setCapability("appActivity", "com.dianping.main.guide.SplashScreenActivity");
        capabilities.setCapability("unicodeKeyboard", "true");   //支持中文输入
        capabilities.setCapability("resetKeyboard", "true");   //支持中文输入，必须两条都输入
        capabilities.setCapability("noSign", "true");    //不重新签名app
        capabilities.setBrowserName("");

        try{
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

    }

    @Test
    public void loginTest() throws Exception {

        MainPage main = new MainPage(driver);
        main.skipIcon();
        main.gotoMyPage();

        MyPage myPage =new MyPage(driver);
        myPage.normalLogin("18302180373","jianjian8924");

    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}