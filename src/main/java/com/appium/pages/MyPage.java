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


	// 点击登录按钮
	@FindBy(id = "login_tip")
	private WebElement clickLoginButton;

	// 正常登录页标题
	@FindBy(name = "账号密码登录")
	private WebElement normalLoginTitleText;

	// 跳转到正常登录页 & 正常登录都是这个id
	@FindBy(id = "login")
	private WebElement loginButton;

	// 正常登陆页用户名编辑框
//	@FindBy(xpath = "//*[contains(@id, 'edit_text')][1]")
//	private WebElement usernameEditText;
	@FindBy(xpath = "//*[contains(@resource-id, 'id/usr')]//*[contains(@resource-id, 'id/edit_text')]")
	private WebElement usernameEditText;

	// 正常登陆页密码编辑框
	@FindBy(xpath = "//*[contains(@resource-id, 'id/psw')]//*[contains(@resource-id, 'id/edit_text')]")
	private WebElement passwordEditText;
//	@FindBy(xpath = "//*[contains(@id, 'edit_text')][1]")
//	private WebElement passwordEditText;

	// 已经登录时的用户名
	@FindBy(id = "user_name")
	private WebElement usernameText;


	public void normalLogin( String username, String password) throws InterruptedException {

		// skip when already login
		BaseUtils.saveScreenshot(driver, "用户名密码登录第一步");
		if (BaseUtils.waitForElementVisibility(driverWait, usernameText)) {
			System.out.println("用户已登录");
			return;
		} else {
			BaseUtils.waitForElement(driverWait, clickLoginButton).click();
		}


		// skip normal login page and go to fast login page
		BaseUtils.saveScreenshot(driver, "用户名密码登录第二步");
		if (BaseUtils.waitForElementVisibility(driverWait, normalLoginTitleText)) {
			BaseUtils.waitForElement(driverWait, loginButton).click();
		}

		BaseUtils.saveScreenshot(driver, "用户名密码登录第三步");
		BaseUtils.waitForElement(driverWait, usernameEditText).sendKeys(username);
		BaseUtils.waitForElement(driverWait, passwordEditText).sendKeys(password);
		BaseUtils.saveScreenshot(driver, "用户名密码登录第四步");

		BaseUtils.saveScreenshot(driver, "用户名密码登录第五步");
		if (BaseUtils.waitForElementVisibility(driverWait, normalLoginTitleText)) {
			BaseUtils.waitForElement(driverWait, loginButton).click();
		}

		//手动输入验证码
		Thread.sleep(8000);

		BaseUtils.saveScreenshot(driver, "用户名密码登录第六步");
		if (BaseUtils.waitForElementVisibility(driverWait, normalLoginTitleText)) {
			BaseUtils.waitForElement(driverWait, loginButton).click();
		}
	}




	public void checkLoginSuccess() {
		Assert.assertTrue(BaseUtils.waitForElementVisibility(driverLongWait, usernameText));
		BaseUtils.saveScreenshot(driver, "CheckLoginSuccess");
	}
}
