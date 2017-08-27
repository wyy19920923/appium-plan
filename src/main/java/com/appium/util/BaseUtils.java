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

//	public static void clickPoint(AndroidDriver driver, double xOffset, double yOffset, int duration) {
//		int screenX = driver.manage().window().getSize().getWidth();
//		int screenY = driver.manage().window().getSize().getHeight();
//		int touchX = (int)(screenX * xOffset);
//		int touchY = (int)(screenY * yOffset);
//		System.out.println("Click (" + touchX + ", " + touchY + ")");
//		driver.tap(1, touchX, touchY, duration);
//	}
//
//	public static void swipeToUp(AndroidDriver driver, int times, int duration) {
//		int screenX = driver.manage().window().getSize().getWidth();
//		int screenY = driver.manage().window().getSize().getHeight();
//		int startX = screenX / 2;
//		int startY = screenY * 3 / 4;
//		int endX = screenX / 2;
//		int endY = screenY / 4;
//		System.out.println("Swipe from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ") " + times + " times");
//		for (int i = 0; i < times; i++) {
//			driver.swipe(startX, startY, endX, endY, duration);
//		}
//	}
//
//	public static void swipeToDown(AndroidDriver  driver, int times, int duration) {
//		int screenX = driver.manage().window().getSize().getWidth();
//		int screenY = driver.manage().window().getSize().getHeight();
//		int startX = screenX / 2;
//		int startY = screenY / 4;
//		int endX = screenX / 2;
//		int endY = screenY * 3 / 4;
//		System.out.println("Swipe from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ") " + times + " times");
//		for (int i = 0; i < times; i++) {
//			driver.swipe(startX, startY, endX, endY, duration);
//		}
//	}
//
//	private static URI buildUri(String uri, String... params) throws Exception {
//		if (params.length % 2 != 0) {
//			throw new Exception("The number of parameters must be an even number!");
//		}
//
//		URIBuilder builder = new URIBuilder(URI.create(uri));
//		for (int i = 0; i < params.length; i += 2) {
//			builder.addParameter(params[i], params[i + 1]);
//		}
//		return builder.build();
//	}
//
	private static String combinePaths(String... paths) {
		File combined = new File("");
		for (int i = 0; i < paths.length; i++) {
			combined = new File(combined, paths[i]);
		}
		return combined.getPath();
	}
	/*
	public static void saveScreenshot(AndroidDriver<AndroidElement> driver, String caseId) {
		saveScreenshot(driver, caseId, "");
	}
	*/
	public static void saveScreenshot(AndroidDriver driver, String keyWord) {
		if (debugMode) {
			return;
		}
		try {
			//Thread.sleep(Constants.SAVE_SCREENSHOT_WAIT_TIME);
			String saveScreenshotDir = combinePaths(CURRENT_DIR, SAVE_SCREENSHOT_DIR_PREFIX, SAVE_SCREENSHOT_DIR_SUFFIX);
			File scrFile;
			scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//			String fileSubDir = "Case" + caseId;
			String fileName = keyWord + ".jpg";
//			String filePath = combinePaths(saveScreenshotDir, fileSubDir, fileName);
			String filePath = combinePaths(saveScreenshotDir, fileName);
			System.out.println("Begin save screenshot");
			FileUtils.copyFile(scrFile, new File(filePath));
			System.out.println("Save screenshot done: " + filePath);
		} catch (Exception e) {
			System.out.println("Cannot save screenshot!");
			e.printStackTrace();
		}
	}
	
//	public static void prepareMockData(String mockDataId) {
//		try {
//			System.out.println("Begin send mockdata: " + mockDataId);
//			String[] params = mockDataId.split("_");
//			if (params.length != 3) {
//				throw new Exception("The number of parameters must be 3!");
//			}
//			//URI uri = buildUri(Constants.MOCK_SERVER_HOST + Constants.MOCK_CONFIG_DO,
//			//	"caseid", mockDataId);
//			URI uri = buildUri(Constants.MOCK_SERVER_HOST + Constants.MOCK_CONFIG_DO,
//				"category_id", params[0], "category_case_id", params[1], "category_case_sub_id", params[2]);
//
//			URL url = new URL(uri.toString());
//			System.out.println(url.toString());
//			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("User-Agent", "Test-Agent");
//			int responseCode = conn.getResponseCode();
//			if (responseCode != 200) {
//				throw new Exception("The responseCode must be 200!");
//			}
//			System.out.println("Send mockdata done: " + mockDataId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static String getLatestVerifyCode(String userMobile) throws Exception {
		try {
			Thread.sleep(3000);
			System.out.println("Begin get verify code for " + userMobile);
			URL url = new URL("http://192.168.217.81:8080/getVerifyCode?mobileno=" + userMobile);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent", "Test-Agent");
			InputStream is = conn.getInputStream();
			String html = getStringFromInputStream(is);
			String code = parseVerifyCodeFromHtmlString(html, userMobile);
			System.out.println(code);
			return code;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getStringFromInputStream(InputStream is) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] buf = new byte[4096];
		int len = -1;
		while ((len = is.read(buf)) != -1) {
			os.write(buf, 0, len);
		}
		String str = os.toString("UTF-8");
		is.close();
		os.close();
		return str;
	}

	// 解析HTML获取验证码，这个方法实现的比较简单，并没有用HTML DOC标准的方式
	private static String parseVerifyCodeFromHtmlString(String str, String userMobile) {
		// the first one would be the latest, and the length is 6
		//String pattern = "<tr><td>" + userMobile + "</td><td>";
		//String code = str.substring(str.indexOf(pattern) + pattern.length(), str.indexOf(pattern) + pattern.length() + 6);
		// 上面的方法已废弃
		// TODO：由于验证码的Message格式会改变，之后需要更好解析方法，如：HTML DOC + REGEX
		String pattern = "（大众点评";
		int index = str.indexOf(pattern);
		String code = str.substring(index - 6, index);
		return code;
	}

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
	/*
	public static void waitForElementPresentAndTryClick(WebDriverWait driver, By locator) {
		try {
			driver.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	public static void waitForElementPresentAndTryClick(WebDriverWait driver, WebElement element) {
		try {
			driver.until(ExpectedConditions.visibilityOf(element)).click();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	public static Boolean waitForTextToBe(WebDriverWait driver, By locator, String text) {
		try {
			driver.until(ExpectedConditions.textToBe(locator, text));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static Boolean waitForTextMatches(WebDriverWait driver, By locator, Pattern pattern) {
		try {
			driver.until(ExpectedConditions.textMatches(locator, pattern));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	*/
	
}
