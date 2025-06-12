
package com.airbase.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.airbase.base.BaseTest;
import com.airbase.pages.HomePage;
import com.airbase.pages.ProductInfoPage;
import com.airbase.pages.SearchResultsPage;

public class AmazonTest extends BaseTest {

	@Test(dataProvider = "brands")
	public void amazonSearchTest(String brand) throws Exception {

		logger.info("Smart watch search test started !!");

		logger.info("Launch amazon.in & verify page title !!");

		driver.get(amzonWebUrl);
		Assert.assertTrue(action.getTitle(driver).contains("Amazon"));

		logger.info("Enter smartwatch in search box & click on search !!");
		HomePage home = new HomePage(driver);
		home.search("smartwatches");

		logger.info("Filter brand & apply price range !!");
		SearchResultsPage results = new SearchResultsPage(driver);
		results.filterByBrand(brand);
		results.setPriceRange("1000", "5000");

		logger.info("Verify applied price range filter !!");
		Assert.assertTrue(results.arePricesWithinRange(100, 5000));

		logger.info("Short price high to low & open highest price product !!");
		results.sortHighToLow();
		Thread.sleep(2000);
		results.openTopProduct();

		logger.info("Switch to new tab !!");
		action.switchToNewWindow(driver);

		logger.info("Added product to the cart & verify !!");
		ProductInfoPage product = new ProductInfoPage(driver);
		product.addToCart();
		Assert.assertTrue(product.isAddedToCart());

		logger.info("close the current window & return to main !!");
		driver.close();
		action.switchToNewWindow(driver);

		logger.info("Amazon Smart Watch Search Test Ended !!");

	}

	@DataProvider(name = "brands")
	public Object[][] brands() {
		return new Object[][] { { "boAt" }/*, { "Fastrack" }, { "Fire-Boltt" }, { "Noise" }*/ };
	}

}
