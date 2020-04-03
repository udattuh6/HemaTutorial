package ownProject.AppiumProject;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;
	
	public static AppiumDriverLocalService startServer() {
		if(!checkIfServerIsRunnning(4723)) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}
	
	public static boolean checkIfServerIsRunnning(int port) {
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
	
	public static void startEmulator() throws IOException, InterruptedException
	{

		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(30000);
	}

	@BeforeMethod
	public void killExistingProcess() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(5000);
	}
	
	public AndroidDriver<AndroidElement> driverCapabilities(String appName, String device) throws IOException, InterruptedException{
		
		System.getProperty("user.dir");
		File file = new File(System.getProperty("user.dir")+"\\src\\" + appName);
		DesiredCapabilities cap = new DesiredCapabilities();
		String deviceN = System.getProperty("deviceName");
		if(deviceN.contains("emu")) {
			startEmulator();
			cap.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());
		}
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	public static void getScreenshot(String testName) throws IOException {
		File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy HH:MM:SS");
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\output\\" + testName + "_" + sdf.format(d).replaceAll(":", "")+"_test.png"));
	}
}
