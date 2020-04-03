package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class GooglePage {
	
	public GooglePage(AndroidDriver<AndroidElement> driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//*[@name='q']")
	private WebElement name;
	
	public void search(String text) {
		name.sendKeys(text);
		name.sendKeys(Keys.ENTER);
	}
}
