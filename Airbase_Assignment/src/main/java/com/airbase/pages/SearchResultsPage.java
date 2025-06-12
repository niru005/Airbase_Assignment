package com.airbase.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.airbase.actiondriver.ActionDriverImpl;

public class SearchResultsPage extends BasePage {
	ActionDriverImpl action;

	@FindBy(id = "p_36/range-slider_slider-item_lower-bound-slider")
	private WebElement minPriceSlider;

	@FindBy(id = "p_36/range-slider_slider-item_upper-bound-slider")
	private WebElement maxPriceSlider;

	@FindBy(css = "input.a-button-input[type='submit']")
	private WebElement goButton;

	@FindBy(css = ".a-price-whole")
	private List<WebElement> priceLists;

	@FindBy(css = "#a-autoid-0-announce")
	private WebElement sortBy;

	@FindBy(xpath = "//a[text()='Price: High to Low']")
	private WebElement priceHighToLow;

	@FindBy(css = "h2.a-size-base-plus.a-spacing-none.a-color-base.a-text-normal")
	private List<WebElement> topProduct;

	public SearchResultsPage(WebDriver driver) {
		super(driver);
		action = new ActionDriverImpl();
	}

	public void filterByBrand(String brandName) {
		By brandLocator = By.xpath("//span[text()='" + brandName + "']");
		driver.findElement(brandLocator).click();
	}

	public void setPriceRange(String min, String max) {
		action.explicitWait(driver, minPriceSlider, 10);
		action.slide(driver, minPriceSlider, min);
		action.explicitWait(driver, maxPriceSlider, 10);
		action.slide(driver, maxPriceSlider, max);
		action.click(goButton, "Go - Submit price range");
	}

	public boolean arePricesWithinRange(int min, int max) {
		for (WebElement price : priceLists) {
			try {
				int value = Integer.parseInt(price.getText().replaceAll("[^0-9]", ""));
				System.out.println("price of smartWatch is : " + value);
				if (value < min || value > max)
					return false;
			} catch (Exception exception) {
				System.out.println("Price didn't match : " + exception.getMessage());
			}
		}
		return true;
	}

	public void sortHighToLow() {
		action.click(sortBy, "Sort by");
		action.click(priceHighToLow, "Price: Low to High");
	}

	public void openTopProduct() {
		System.out.print("top product : " + topProduct.get(0));
		action.click(topProduct.get(0), "Watch with top price");
	}

}
