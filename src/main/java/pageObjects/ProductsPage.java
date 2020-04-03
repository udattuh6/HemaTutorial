package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsPage {
	
	public ProductsPage(AndroidDriver<AndroidElement> driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> productAddToCartList;

	@AndroidFindBy(className = "android.widget.RelativeLayout")
	private List<WebElement> productsList;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
	private List<WebElement> productTitlesList;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement buttonAddToCart;
	
	public void selectProductsRandomly(int randomNumber) {
		for(int i=0; i< randomNumber; i++) {
			productAddToCartList.get(i).click();
		}
	}
	
	public void selectProducts(String productName) throws InterruptedException {
//		System.out.println(productsList.size());
		for(int i=0; i< productsList.size(); i++) {
//			System.out.println(productTitlesList.get(i).getText());
			if(productTitlesList.get(i).getText().contains(productName)){
//				productAddToCartList.get(i).click();
//				System.out.println(i);
				productsList.get(i+1).findElement(By.xpath("//*[@text='ADD TO CART']")).click();
				Thread.sleep(3000);
				break;
			}
		}
	}
	
	public void selectCart() {
		buttonAddToCart.click();
	}
}
