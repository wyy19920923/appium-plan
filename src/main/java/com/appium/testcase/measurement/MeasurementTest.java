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
import org.openqa.selenium.WebElement;
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
public class MeasurementTest {

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
        Logger logger = Logger.getLogger(MeasurementTest.class);

        //登录数据源
        ExcelReader logindata = new ExcelReader("D:\\intellijworkspace\\appium-plan\\src\\main\\resources\\data.xls","logindata");
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
        main.gotoMyPage();

        //先进行登录，登录完成以后进入健康页面
        MyPage myPage =new MyPage(driver);
//        myPage.normalLogin(logindata.getCellData(2, 1),logindata.getCellData(2, 2));
        Thread.sleep(3000);
        myPage.gotoHealthPage();

        //健康页面点击“中医治未病”进入测试页面
        HealthPage health = new HealthPage(driver);
        health.gotoMeasurementTest();
        BaseUtils.switchToWebview(driver,"WEBVIEW_com.wonders.health.venus.open.user");

        for (int i=1;i<33;i++){
            Random r = new Random();
//            driver.findElementByXPath("//input[@value=" + list.get(int(r.nextInt(list.size())) + "]").click();
            Thread.sleep(5000);
//            driver.findElementByXPath("//li[@value=" + list.get(r.nextInt(list.size())) + "]").click();
//            driver.findElementByXPath("//label[@class='radio']/" + list.get(r.nextInt(list.size()))).click();
//            driver.findElementByXPath("//label[@class='radio']" +"/[" +list.get(r.nextInt(list.size()))+"]").click();
//            driver.findElement(By.xpath("//label[@class='radio']"));
//            List<WebElement> elements = driver.findElementsByXPath("//li[@key='tzbs1']");
//            logger.debug("获取到的元素个数为"+elements.size());
//            logger.debug("执行到这儿说明查找元素没问题");
//            elements.get(1).click();
//            elements.get(2).click();
//            elements.get(3).click();
            logger.debug("开始查找元素，答题");
            driver.findElement(By.xpath("//li[@key='tzbs1'][1]")).click();
            driver.findElement(By.xpath("//li[@key='tzbs2'][3]")).click();
            driver.findElement(By.xpath("//li[@key='tzbs3'][2]")).click();
            driver.findElement(By.xpath("//li[@key='tzbs4'][1]")).click();
            logger.debug(driver.findElement(By.xpath("//li[@key='tzbs1'][1]")).isSelected());


/*            logger.debug("开始查找元素，答题");
            int widht = driver.manage().window().getSize().getWidth();
            int height = driver.manage().window().getSize().getHeight();
            driver.tap(1,widht/2,height/2,1000);
            Thread.sleep(1000);*/
//            elements.get(list.get(r.nextInt(list.size())));

        }


    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}
