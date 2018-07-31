package gov.census.tests;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

import gov.census.pages.HomePage;
import gov.census.utilities.ConfigurationReader;
import gov.census.utilities.Driver;

public class TestBase {
	WebDriver driver;
	Actions actions;

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		driver = Driver.getDriver();
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();

		driver.get(ConfigurationReader.getProperty("url"));
		HomePage homePage = new HomePage();
		
		try {
			driver.getCurrentUrl();
		}catch(NoSuchElementException e){
			homePage.closeSubDialogPopUp.click();
		}
		
	}
	@AfterClass
	public void tearDown() {
	Driver.getDriver().close();
	}
}
