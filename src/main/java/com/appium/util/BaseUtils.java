package com.appium.util;

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

/**
 * Created by wangyuying on 2016/4/28.
 */
public class BaseUtils {

	private BaseUtils() {}
	
	private static final Boolean debugMode = false; // false: save screenshot

	//private static final SimpleDateFormat SHORT_SDF = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat LONG_SDF = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private static final String CURRENT_DIR = System.getProperty("user.dir");
	private static final String SAVE_SCREENSHOT_DIR_PREFIX = "screenshot";
	private static final String SAVE_SCREENSHOT_DIR_SUFFIX = LONG_SDF.format(new Date());



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
			scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
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

//	public static void clickPoint(AndroidDriver driver, double xOffset, double yOffset, int duration) {
//		int screenX = driver.manage().window().getSize().getWidth();
//		int screenY = driver.manage().window().getSize().getHeight();
//		int touchX = (int)(screenX * xOffset);
//		int touchY = (int)(screenY * yOffset);
//		System.out.println("Click (" + touchX + ", " + touchY + ")");
//		driver.tap(1, touchX, touchY, duration);
//	}
//
	public static void swipeToLeft(AndroidDriver driver, int times, int duration) {
		int widht  = driver.manage().window().getSize().getWidth();
		int height  = driver.manage().window().getSize().getHeight();
		int startX = widht*6/7;
		int startY = height/2;
		int endX = widht/7;
		int endY = height/2;
		System.out.println("Swipe from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ") " + times + " times");
		for (int i = 0; i < times; i++) {
			driver.swipe(startX, startY, endX, endY, duration);
		}
	}


//	private static String getStringFromInputStream(InputStream is) throws IOException {
//		ByteArrayOutputStream os = new ByteArrayOutputStream();
//		byte[] buf = new byte[4096];
//		int len = -1;
//		while ((len = is.read(buf)) != -1) {
//			os.write(buf, 0, len);
//		}
//		String str = os.toString("UTF-8");
//		is.close();
//		os.close();
//		return str;
//	}


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
	
}
