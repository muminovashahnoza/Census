package gov.census.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gov.census.utilities.Driver;

public class HomePage {
	public HomePage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(partialLinkText = "Audio")
	public WebElement audio;
	@FindBy(xpath = "//button[@title='Close subscription dialog']")
	public WebElement closeSubDialogPopUp;
	//// E[starts-with(@A, 't')]
	// "https://www.census.gov/library.html"
	// //a[@tabindex='121']
	@FindBy(xpath = "//div[contains(text(),' Library ')]")
	public WebElement libraryLink;

	@FindBy(xpath = "//ul[@class='nav nav-pills']")
	public WebElement libraryList;
	// li[@class='categories subCategoryButton']
	@FindBy(xpath = "(//ul[@class='nav nav-pills'])[3]//a")
	public List<WebElement> libOptions;
	@FindBy(xpath = "(//ul[@class='nav nav-pills'])[3]//a")
	public WebElement option1;
	@FindBy(xpath = "(//li[@class='categories subCategoryButton'])[29]//a")
	public WebElement linkInfoAndVisualizations;

	public static List<WebElement> getLibraryOptions() {
		List<String> list = new ArrayList<>();
		List<WebElement> l1 = Driver.getDriver().findElements(By.xpath(("(//ul[@class='nav nav-pills'])[3]//a")));
		List<WebElement> l2 = Driver.getDriver().findElements(By.xpath(("(//ul[@class='nav nav-pills'])[4]//a")));
		l1.addAll(l2);

		return l1;
	}

}
