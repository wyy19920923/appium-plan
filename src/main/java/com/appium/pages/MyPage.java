package com.appium.pages;

import com.appium.util.BaseUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by wangyuying on 2017/8/27.
 */
public class MyPage extends BasePage   {

	public MyPage(AndroidDriver driver) {
		super(driver);
	}


	// 点击头像进行登录操作
	@FindBy(id = "civ_photo")
	private WebElement clickLoginButton;

/*	// 动态码登录方式
	@FindBy(name = "rd_login_by_code")
	private WebElement codeloginButton;

	//密码登录方式
	@FindBy(id = "rd_login_by_pwd")
	private WebElement pwdloginButton;

	//动态码登录&密码登录用户名
	@FindBy(id = "edit_mobile")
	private WebElement usernameEditText;

	// 动态码登录-验证码
	@FindBy(id = "edit_code")
	private WebElement codeEditText;

	// 密码登录-密码
	@FindBy(id = "edit_pwd")
	private WebElement passwordEditText;

	// 登录按钮
	@FindBy(id = "btn_login")
	private WebElement loginButton;

	// 已经登录时用户名的id
	@FindBy(id = "tv_name")
	private WebElement usernameid;*/

	// 已经登录时用户名的id
	@FindBy(id = "tv_name")
	private WebElement usernameid;

	// 登录完成以后的健康按钮
	@FindBy(id = "tab_container_1")
	private WebElement healthPageButton;


	public void gotoLoginPage() throws InterruptedException {

		// skip when already login
		BaseUtils.saveScreenshot(driver, "点击头像进行登录");
		if (BaseUtils.waitForElementVisibility(driverWait, usernameid)) {
			System.out.println("用户已登录，无需点击头像登录");
			return ;
		} else {
			BaseUtils.waitForElement(driverWait, clickLoginButton).click();
		}
	}


	public void gotoHealthPage() {
		BaseUtils.waitForElement(driverWait, healthPageButton).click();
	}

	public String getLoginUserName() throws InterruptedException {

		return BaseUtils.waitForElement(driverWait, usernameid).getText();
	}

}
