package com.appium.testcase.login;

import com.appium.pages.LoginPage;
import com.appium.pages.MainPage;
import com.appium.pages.MyPage;
import com.appium.util.ExcelReader;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

//import org.openqa.selenium.By;

/**
 * Created by wangyuying on 2017/8/27.
 */
public class LoginTest2 {

    
    private static final String APK_HOME = "D:\\intellijworkspace\\appium-plan\\src\\main\\resources\\health.apk";
    public AndroidDriver driver;
    ExcelReader logindata = new ExcelReader("D:\\intellijworkspace\\appium-plan\\src\\main\\resources\\data.xls","logindata");



    @BeforeTest
    public void setUp() throws Exception{

        //设置appium启动项
        //load apk file
        File apk = new File(APK_HOME);

        DesiredCapabilities capabilities =new  DesiredCapabilities();
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("app", apk.getAbsolutePath());
        capabilities.setCapability("deviceName", "864394010761874");      //模拟器IMEI
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "4.4.2");
        capabilities.setCapability("uuid", "127.0.0.1:62001");    //设备uuid
        capabilities.setCapability("appPackage", "com.wonders.health.venus.open.user");
        capabilities.setCapability("appActivity", "com.wonders.health.venus.open.user.module.launch.SplashActivity");
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


    @Test(priority = 0)
    public void GotoLoginPage() throws Exception {

        PropertyConfigurator.configure("D:\\intellijworkspace\\appium-plan\\log4j.properties");
        Logger logger = Logger.getLogger(LoginTest2.class);


        //logger.getLogger("打开app进入首页");
        logger.debug("打开app进入首页");
        MainPage main = new MainPage(driver);
        main.gotoMyPage();

        MyPage myPage =new MyPage(driver);
        myPage.gotoLoginPage();

    }


    @Test(priority = 1)
    public void loginWithIllegalUserName() throws Exception {

        PropertyConfigurator.configure("D:\\intellijworkspace\\appium-plan\\log4j.properties");
        Logger logger = Logger.getLogger(LoginTest2.class);

        LoginPage loginpage = new LoginPage(driver);
        loginpage.normalLogin(logindata.getCellData(2, 1),logindata.getCellData(2, 2));

        logger.debug("开始断言");
        Thread.sleep(3000);
//        String text1 = driver.findElementByXPath("//android.widget.TextView[contains(@text,'请输入')]").getText();
        Assert.assertEquals(loginpage.getErrorMessage(), logindata.getCellData(2, 3));
    }

    @Test(priority = 2)
    public void loginWithIllegalPassWord() throws Exception {

        PropertyConfigurator.configure("D:\\intellijworkspace\\appium-plan\\log4j.properties");
        Logger logger = Logger.getLogger(LoginTest2.class);

        LoginPage loginpage = new LoginPage(driver);
        loginpage.normalLogin(logindata.getCellData(3, 1),logindata.getCellData(3, 2));

        logger.debug("开始断言");
//        Assert.assertEquals(logindata.getCellData(3, 3),loginpage.getErrorMessage());
        Thread.sleep(3000);
        Assert.assertEquals(loginpage.getErrorMessage(),logindata.getCellData(3, 3));

    }

    @Test(priority = 3)
    public void loginSuccess() throws Exception {

        PropertyConfigurator.configure("D:\\intellijworkspace\\appium-plan\\log4j.properties");
        Logger logger = Logger.getLogger(LoginTest2.class);

        LoginPage loginpage = new LoginPage(driver);
        loginpage.normalLogin(logindata.getCellData(5, 1),logindata.getCellData(5, 2));

        logger.debug("开始断言");
//        Assert.assertEquals( driver.findElement(By.id("tv_name")).getText(),logindata.getCellData(5, 3));
        Assert.assertEquals(loginpage.getLoginUserName(),logindata.getCellData(5, 3));

    }

/*    @Test(priority = 4)
    public void loginSuccess() throws Exception {

        PropertyConfigurator.configure("D:\\intellijworkspace\\appium-plan\\log4j.properties");
        Logger logger = Logger.getLogger(LoginTest.class);

        LoginPage loginpage = new LoginPage(driver);
        loginpage.normalLogin(logindata.getCellData(5, 1),logindata.getCellData(5, 2));

        logger.debug("开始断言");
//        Assert.assertEquals( driver.findElement(By.id("tv_name")).getText(),logindata.getCellData(5, 3));
        Assert.assertEquals(loginpage.getLoginUserName(),logindata.getCellData(5, 3));

    }*/


    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}