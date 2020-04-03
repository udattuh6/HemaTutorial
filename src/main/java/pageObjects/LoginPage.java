package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {
	
	public LoginPage(AppiumDriver<AndroidElement> driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement name;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement radioMale;

	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement radioFemale;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement buttonLetsShop;
	
	public WebElement getName() {
		return name;
	}
	
	public WebElement getGenderMale() {
		return radioMale;
	}
	
	public WebElement getLetsShopButton() {
		return buttonLetsShop;
	}
	
	public void enterName(String name) {
		getName().isDisplayed();
		getName().sendKeys(name);
	}

	public void selectGenderMale() {
		getGenderMale().click();
	}
	
	public void clickOnLetsShop() {
		getLetsShopButton().click();
	}
	
}
