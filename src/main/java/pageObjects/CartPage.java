package pageObjects;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import static io.appium.java_client.touch.TapOptions.tapOptions;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class CartPage {
	
	public CartPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id="com.androidsample.generalstore:id/rvCartProductList")
	private List<WebElement> listOfProducts;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPriceList;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalPrice;

	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement tcCheckbox;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement buttonProceed;
	
	public double calculateTotalPrice() {
		double total=0;
		
		for(int i=0;i<productPriceList.size();i++) {
			
			total = total + (Double.parseDouble(productPriceList.get(i).getText().substring(1))) ;
		}
		return total;
	}
	
	public double getTotalPrice() {
		return Double.parseDouble(totalPrice.getText().substring(1));
	}
	
	public void selectTermsAndConditions(AndroidDriver<AndroidElement> driver) {
		TouchAction<?> action= new TouchAction(driver);
		action.tap(tapOptions().withElement(element(tcCheckbox))).perform();
//		tcCheckbox.click();
	}
	
	public void clickOnProceed() throws InterruptedException {
		buttonProceed.click();
		Thread.sleep(30000);
	}
	
	public void navigateToWebPage(AndroidDriver<AndroidElement> driver) {
		Set<String> context = driver.getContextHandles();
		for(String contextName :context) {
			System.out.println(contextName);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");
	}
}
