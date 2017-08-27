package com.appium.pages;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by wangyuying on 2017/8/27.
 */
public class BasePage {
	
	public AndroidDriver driver;
	public WebDriverWait driverWait;
	public WebDriverWait driverLongWait;
	
	public BasePage(AndroidDriver driver) {
		this.driver = driver;
		this.driverWait = new WebDriverWait(this.driver,3);
		this.driverLongWait = new WebDriverWait(this.driver,10);
		PageFactory.initElements(this.driver, this);
	}
}
