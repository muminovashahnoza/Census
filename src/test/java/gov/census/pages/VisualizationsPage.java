package gov.census.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.github.javafaker.Faker;

import gov.census.utilities.Driver;
import gov.census.utilities.Utils;

public class VisualizationsPage {

	public VisualizationsPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(xpath = "//div[@class='valignMiddle']")
	public List<WebElement> allPics;

	@FindBy(linkText = "Infographics & Visualizations")
	public WebElement infoAndVisiButton;

	@FindBy(linkText = "Interactive Gallery")
	public WebElement interactiveGallery;

	@FindBy(xpath = "//div[@id='dropDownButton']")
	public WebElement yearsDropDownButton;

	@FindBy(id = "socialLinkTwitter")
	public WebElement twitter;

	@FindBy(id = "socialLinkFacebook")
	public WebElement facebook;

	@FindBy(id = "socialLinkPinit")
	public WebElement pinterest;

	@FindBy(xpath = "//a[@id='All']")
	public WebElement allYears;

	@FindBy(xpath = "//div[contains(text(),'Happy New Year 2017')]")
	public WebElement happyNewYear2017;

	// use for current page value
	@FindBy(xpath = "//span[@id='currentPageSpan']")
	public WebElement currentPage;

	// <h2 class="sh-h2">Infographics &amp; Visualizations</h2>
	@FindBy(xpath = "//h2[@class='sh-h2']")
	public WebElement contentOfPageText;

	@FindBy(xpath = "//div[@class='paginationInfo']//ul")
	public WebElement countPagesBlock;

	@FindBy(xpath = "//span[@id='currentPageSpan']")
	public WebElement currentPageCount;

	@FindBy(xpath = "//li[@class='nextButton']")
	public WebElement nextPage;

	@FindBy(xpath = "//li[@class='prevButton']")
	public WebElement prevPage;

	@FindBy(xpath = "//a[@id='2017']")
	public WebElement button2017;

	@FindBy(xpath = "//a[@id='2016']")
	public WebElement button2016;
	@FindBy(id = "2015")
	public WebElement button2015;

	@FindBy(xpath = "(//div[@class='valignMiddle'])[1]")
	public WebElement censusNavigator;

	@FindBy(xpath = "//select[@id='listFilterSelect']")
	public WebElement selectYear;

	@FindBy(xpath = "//time[@class='post-date']")
	public WebElement currectPageDate;

	@FindBy(xpath = "//*[@id='visualHeaders']/div[1]/div/h2")
	public WebElement currectPicData;

	public static void clickOnRandomImage() {
		// (//div[@class='valignMiddle'])[7] 1-20
		int random = new Faker().random().nextInt(1, 20);
		WebElement randomPic = Driver.getDriver()
				.findElement(By.xpath("(//div[@class='valignMiddle'])[" + random + "]"));
		Utils.clickJSE(randomPic);

	}

	public static void clickOnRandomYear() {
		// *[@id=\"listMenu\"]/li[2-6]
		// *[@id="tabul"]/li[1]
		int random = new Faker().random().nextInt(2, 7);
		WebElement randomYear = Driver.getDriver().findElement(By.xpath("//*[@id=\"listMenu\"]/li[" + random + "]"));

		WebElement randomSlideYear = Driver.getDriver().findElement(By.id("dropDownButton"));

		WebElement randomSlideYearOptions = Driver.getDriver()
				.findElement(By.xpath("//*[@id='tabul']/li[" + random + "]"));

		if (random == 7) {
			random = new Faker().random().nextInt(1, 20);
			Utils.clickJSE(randomSlideYear);
			Utils.waitFor(2);
			Utils.clickJSE(randomSlideYearOptions);
		} else {
			Utils.clickJSE(randomYear);
		}

	}

}
