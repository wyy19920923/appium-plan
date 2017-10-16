package com.appium.util;

//import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.utils.URIBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Created by wangyuying on 2016/4/28.
 */
public class BaseUtils {

	private BaseUtils() {
	}

	private static final Boolean debugMode = false; // false: save screenshot

	//private static final SimpleDateFormat SHORT_SDF = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat LONG_SDF = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private static final String CURRENT_DIR = System.getProperty("user.dir");
	private static final String SAVE_SCREENSHOT_DIR_PREFIX = "screenshot";
	private static final String SAVE_SCREENSHOT_DIR_SUFFIX = LONG_SDF.format(new Date());


	public static WebElement waitForElement(WebDriverWait driver, By selector) {
		return driver.until(ExpectedConditions.presenceOfElementLocated(selector));
	}

	public static WebElement waitForElement(WebDriverWait driver, WebElement element) {
		return driver.until(ExpectedConditions.visibilityOf(element));
	}


	public static Boolean waitForElementVisibility(WebDriverWait driver, By locator) {
		try {
			driver.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Boolean waitForElementVisibility(WebDriverWait driver, WebElement element) {
		try {
			driver.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Boolean waitForTextToBePresentInElement(WebDriverWait driver, WebElement element, String text) {
		try {
			driver.until(ExpectedConditions.textToBePresentInElement(element, text));
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	private static String combinePaths(String... paths) {
		File combined = new File("");
		for (int i = 0; i < paths.length; i++) {
			combined = new File(combined, paths[i]);
		}
		return combined.getPath();
	}

	public static void saveScreenshot(AndroidDriver driver, String keyWord) {
		try {
			String saveScreenshotDir = combinePaths(CURRENT_DIR, SAVE_SCREENSHOT_DIR_PREFIX, SAVE_SCREENSHOT_DIR_SUFFIX);
			File scrFile;
			scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String fileName = keyWord + ".jpg";
			String filePath = combinePaths(saveScreenshotDir, fileName);
			System.out.println("Begin save screenshot");
			FileUtils.copyFile(scrFile, new File(filePath));
			System.out.println("Save screenshot done: " + filePath);
		} catch (Exception e) {
			System.out.println("Cannot save screenshot!");
			e.printStackTrace();
		}
	}


	public static void swipeToLeft(AndroidDriver driver, int times, int duration) {
		int widht = driver.manage().window().getSize().getWidth();
		int height = driver.manage().window().getSize().getHeight();
		int startX = widht * 6 / 7;
		int startY = height / 2;
		int endX = widht / 7;
		int endY = height / 2;
		System.out.println("Swipe from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ") " + times + " times");
		for (int i = 0; i < times; i++) {
			driver.swipe(startX, startY, endX, endY, duration);
//			driver.performTouchAction(new TouchAction(driver).press(startX, startY).waitAction(duration).moveTo(endX, endY).release());
		}
	}

	public static void switchToWebview(AndroidDriver driver,String webview) {

		Set<String> context = driver.getContextHandles();
		for (String contextName : context) {
			System.out.println(contextName);
//			if (context.contains("WEBVIEW")) {
			if (contextName.equals(webview)) {
//			if (context.toString().toLowerCase().startsWith("webview")) {
				driver.context(webview);
				System.out.println("切换到webview模式成功");
				return;
			}
			else{
				System.out.println("切换到webview模式失败");
			}
		}
	}


	public static void switchToNative(AndroidDriver driver) {

		Set<String> context = driver.getContextHandles();
		for (String contextName : context) {
			System.out.println(contextName);
			if (context.contains("NATIVE_APP")) {
				driver.context("NATIVE_APP");
				System.out.println("切换到native模式");
			}
		}
	}

	public static boolean findToast(AndroidDriver driver, String toast) {

		try {
			final WebDriverWait wait = new WebDriverWait(driver, 3);
			Assert.assertNotNull(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[contains(@text,'" + toast + "')]"))));
			System.out.println("找到了toast");
			return  true;
		} catch (Exception e) {
			throw new AssertionError("找不到" + toast);
		}
	}



	
}
