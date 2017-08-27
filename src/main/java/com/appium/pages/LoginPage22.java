package com.appium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by wangyuying on 2017/8/27.
 */
public class LoginPage22 {


        // private WebDriver driver;

        //private final String url = "http://www.baidu.com";

        @FindBy(id="kw")
        private WebElement searchField;

        @FindBy(id="su")
        private WebElement baidSearchButton;

        @FindBy(xpath="//a[text()='登录']")
        private WebElement Login;

        @FindBy(id="TANGRAM__PSP_8__userName")
        private WebElement userNameText;

        @FindBy(id="TANGRAM__PSP_8__password")
        private WebElement passWordText;

        @FindBy(id="TANGRAM__PSP_8__submit")
        private WebElement loginButton;


//        public BaiduIndexPage(WebDriver driver,String url) {
//            // this.driver = driver;
//            driver.get(url);
//            PageFactory.initElements(driver, this);
//        }

        public void searchFor(String text) {
            searchField.clear();
            searchField.sendKeys(text);
            baidSearchButton.submit();
        }

        public void Login(String username,String pwd) {

            System.out.println("开始等待3秒");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Login.click();
            userNameText.clear();
            userNameText.sendKeys(username);

            passWordText.clear();
            passWordText.sendKeys(pwd);

            loginButton.submit();
        }


}
