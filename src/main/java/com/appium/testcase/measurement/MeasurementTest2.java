package com.appium.testcase.measurement;

import com.appium.pages.HealthPage;
import com.appium.pages.MainPage;
import com.appium.pages.MyPage;
import com.appium.util.BaseUtils;
import com.appium.util.ExcelReader;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wangyuying on 2017/9/24.
 */
public class MeasurementTest2 {

    private static final String APK_HOME = "D:\\intellijworkspace\\appium-plan\\src\\main\\resources\\health.apk";
    public AndroidDriver driver;

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

    @Test
    public void MeasurementTest() throws Exception {

        PropertyConfigurator.configure("D:\\intellijworkspace\\appium-plan\\log4j.properties");
        Logger logger = Logger.getLogger(MeasurementTest2.class);

        //随机选择一个答案
        List<Integer> list = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }};

        //logger.getLogger("打开app进入首页");
        logger.debug("打开app进入首页");
        MainPage main = new MainPage(driver);
        main.gotoHealthPage();

        //健康页面点击“中医治未病”进入测试页面
        HealthPage health = new HealthPage(driver);
        health.gotoMeasurementTest();
        BaseUtils.switchToWebview(driver,"WEBVIEW_com.wonders.health.venus.open.user");

        Thread.sleep(5000);
        logger.debug("开始答题");
        driver.findElement(By.xpath("//li[@key='tzbs1'][contains(@text,'没有')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@key='tzbs1'][contains(@text,'没有')]")).click();
//        driver.findElement(By.xpath("//li[@key='tzbs2'][1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@key='tzbs1'][contains(@text,'没有')]")).click();
//        driver.findElement(By.xpath("//li[@key='tzbs3'][1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@key='tzbs1'][contains(@text,'没有')]")).click();
//        driver.findElement(By.xpath("//li[@key='tzbs4'][1]")).click();
        Thread.sleep(2000);





    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}
