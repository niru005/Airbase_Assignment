package com.airbase.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.airbase.actiondriver.ActionDriver;
import com.airbase.actiondriver.ActionDriverImpl;

public class HomePage extends BasePage {

	ActionDriver action;

	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchBox;

	@FindBy(id = "nav-search-submit-button")
	private WebElement searchButton;

	public HomePage(WebDriver driver) {
		super(driver);
		action = new ActionDriverImpl();
	}

	public void search(String keyWord) {
		action.type(searchBox, keyWord, "Search Box");
		action.click(searchButton, "Search Button");
	}

}
