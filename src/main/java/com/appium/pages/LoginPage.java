package com.appium.pages;

import com.appium.util.BaseUtils;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by wangyuying on 2017/8/27.
 */
public class LoginPage extends BasePage   {

	public LoginPage(AndroidDriver driver) {
		super(driver);
	}



	// 动态码登录方式
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
	private WebElement usernameid;

	// 登录异常时提示信息
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'位')]")
	private WebElement ErrorMessage;



	public void normalLogin( String username, String password) throws InterruptedException {

		// skip when already login
		BaseUtils.saveScreenshot(driver, "用户名密码登录第一步");
		if (BaseUtils.waitForElementVisibility(driverWait, usernameid)) {
			System.out.println("用户已登录");
			return;
		}

		// 切换到用户名密码登录
		BaseUtils.saveScreenshot(driver, "用户名密码登录第二步");
		if (BaseUtils.waitForElementVisibility(driverWait, pwdloginButton)) {
			BaseUtils.waitForElement(driverWait, pwdloginButton).click();
		}

		BaseUtils.saveScreenshot(driver, "用户名密码登录第三步");
		BaseUtils.waitForElement(driverWait, usernameEditText).clear();
		BaseUtils.waitForElement(driverWait, usernameEditText).sendKeys(username);
		BaseUtils.waitForElement(driverWait, passwordEditText).clear();
		BaseUtils.waitForElement(driverWait, passwordEditText).sendKeys(password);
		BaseUtils.saveScreenshot(driver, "用户名密码登录第四步");


		BaseUtils.saveScreenshot(driver, "用户名密码登录第五步");
		if (BaseUtils.waitForElementVisibility(driverWait, loginButton)) {
			BaseUtils.waitForElement(driverWait, loginButton).click();
		}

	}

	public String getErrorMessage() throws InterruptedException {

		return BaseUtils.waitForElement(driverWait, ErrorMessage).getText();
	}

	public String getLoginUserName() throws InterruptedException {

		return BaseUtils.waitForElement(driverWait, usernameid).getText();
	}


}
