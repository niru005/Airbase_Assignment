package com.airbase.actiondriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ActionDriver {

	void click(WebElement element, String elementName);

	void type(WebElement element, String text, String elementName);

	String getText(WebElement element, String elementName);

	boolean isDisplayed(WebElement element, String elementName);

	public void slide(WebDriver driver, WebElement ele, String position);

	public void explicitWait(WebDriver driver, WebElement element, int timeOut);

	public boolean switchToNewWindow(WebDriver driver);

	public String getTitle(WebDriver driver);

	public String getPageSource(WebDriver driver);

}
