package com.airbase.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.airbase.actiondriver.ActionDriverImpl;
import com.airbase.utils.ConfigReader;
import com.airbase.utils.DriverFactory;

public class BaseTest {
	protected WebDriver driver;
	protected String amzonWebUrl;
	protected ActionDriverImpl action;
	protected Logger logger;

	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) throws IOException {
		// String browser = ConfigReader.getProperty("browser");
		amzonWebUrl = ConfigReader.getProperty("url");
		action = new ActionDriverImpl();
		driver = DriverFactory.initDriver(browser);
		logger = LogManager.getLogger(this.getClass());
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenShots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

}
