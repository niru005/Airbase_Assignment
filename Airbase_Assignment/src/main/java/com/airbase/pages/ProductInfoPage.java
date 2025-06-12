package com.airbase.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.airbase.actiondriver.ActionDriverImpl;

public class ProductInfoPage extends BasePage {
	ActionDriverImpl action;

	@FindBy(xpath = "(//input[@name='submit.add-to-cart'])[2]")
	private WebElement addToCart;

	public ProductInfoPage(WebDriver driver) {
		super(driver);
		action = new ActionDriverImpl();
	}

	public void addToCart() {
		action.explicitWait(driver, addToCart, 10);
		action.click(addToCart, "Add to cart");
	}

	public boolean isAddedToCart() {
		return action.getPageSource(driver).contains("Added to Cart");
	}

}
