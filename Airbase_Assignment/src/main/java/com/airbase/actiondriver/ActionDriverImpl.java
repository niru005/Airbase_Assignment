package com.airbase.actiondriver;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionDriverImpl implements ActionDriver {

	@Override
	public void click(WebElement element, String elementName) {
		try {
			element.click();
			System.out.println("Clicked on: " + elementName);
		} catch (Exception e) {
			System.out.println("Error clicking: " + elementName);
			throw e;
		}
	}

	@Override
	public void type(WebElement element, String text, String elementName) {
		try {
			element.clear();
			element.sendKeys(text);
			System.out.println("Typed '" + text + "' in: " + elementName);
		} catch (Exception e) {
			System.out.println("Error typing in: " + elementName);
			throw e;
		}
	}

	@Override
	public String getText(WebElement element, String elementName) {
		return element.getText();
	}

	@Override
	public boolean isDisplayed(WebElement element, String elementName) {
		return element.isDisplayed();
	}

	@Override
	public void slide(WebDriver driver, WebElement webElement, String minPrice) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'))",
				webElement, minPrice);

	}

	@Override
	public void explicitWait(WebDriver driver, WebElement element, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	@Override
	public boolean switchToNewWindow(WebDriver driver) {
		boolean flag = false;
		try {

			Set<String> s = driver.getWindowHandles();
			Object popup[] = s.toArray();
			driver.switchTo().window(popup[1].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				System.out.println("Window is Navigated with title");
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}

	@Override
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	@Override
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

}
