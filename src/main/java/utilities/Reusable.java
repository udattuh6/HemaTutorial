package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Reusable {
	
	AndroidDriver<AndroidElement> driver;
	
	public Reusable(AndroidDriver<AndroidElement> driver){
		this.driver = driver;
	}
	
	public WebElement selectFromDropdown(WebDriver driver, String containedText) {
		return driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))")); 
	}
	
	public void elementClick(WebElement element) {
		element.click();
	}
	
	public void selectUsingText(String text) {
		driver.findElement(By.xpath("//*[@text='"+text+"']")).click();
	}
	
	public String handleToastAndGetMessages() {
		return driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
	}
}
