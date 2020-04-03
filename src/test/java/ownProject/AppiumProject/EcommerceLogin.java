package ownProject.AppiumProject;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import pageObjects.CartPage;
import pageObjects.GooglePage;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import utilities.Reusable;

public class EcommerceLogin extends Base {

	@Test
//	(dataProvider="InputData", dataProviderClass = TestData.class)
	public void testLogin() throws IOException, InterruptedException {
		AppiumDriverLocalService service = startServer();
		AndroidDriver<AndroidElement> driver = driverCapabilities("original.apk", "emu");

		LoginPage login = new LoginPage(driver);
		login.enterName("db");
		login.selectGenderMale();
		Reusable utilities = new Reusable(driver);
		utilities.selectFromDropdown(driver, "Antarctica");
		utilities.selectUsingText("Antarctica");
		driver.hideKeyboard();
		login.clickOnLetsShop();

		ProductsPage productsPage = new ProductsPage(driver);
		productsPage.selectProducts("Air Jordan 1 Mid SE");
		utilities.selectFromDropdown(driver, "Jordan 6 Rings");
		productsPage.selectProducts("Jordan 6 Rings");
		productsPage.selectCart();
		CartPage cart = new CartPage(driver);
		Assert.assertEquals(cart.calculateTotalPrice(), cart.getTotalPrice());
		cart.selectTermsAndConditions(driver);
//		cart.clickOnProceed();
//		cart.navigateToWebPage(driver); GooglePage google = new GooglePage(driver);
//		google.search("game"); driver.pressKey(new KeyEvent(AndroidKey.BACK));
//		driver.context("NATIVE_APP");
		
		service.stop();
	}

	@Test
	public void testLoginError() throws IOException, InterruptedException {
		AppiumDriverLocalService service = startServer();
		AndroidDriver<AndroidElement> driver = driverCapabilities("original.apk", "emu");

		LoginPage login = new LoginPage(driver);
		driver.hideKeyboard();
		login.clickOnLetsShop();
		Reusable utilities = new Reusable(driver);
		String error = utilities.handleToastAndGetMessages();
		Assert.assertEquals(error, "Please enter your name");
		service.stop();
	}
}
