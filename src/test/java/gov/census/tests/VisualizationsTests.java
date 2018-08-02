package gov.census.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import afu.org.checkerframework.checker.units.UnitsTools;
import bsh.util.Util;
import gov.census.pages.HomePage;
import gov.census.pages.VisualizationsPage;
import gov.census.utilities.Driver;
import gov.census.utilities.Utils;

public class VisualizationsTests extends TestBase {

	Actions action;
	// Verify Link of Infographics & Visualizations page TEST"TC004
    // another commit shahnoza other
	VisualizationsPage vp = new VisualizationsPage();
	HomePage homePage = new HomePage();

	public void gotoVisualizationPage() {

		Utils.clickJSE(homePage.libraryLink);

		Utils.clickJSE(homePage.linkInfoAndVisualizations);
	}

	// Interactive Gallery"TC17
	// Check if "Interactive Gallery" link is visible
	@Test
	public void interactiveGalleryIsVisible() {
		Utils.clickJSE(homePage.libraryLink);
		Utils.clickJSE(homePage.audio);
		Utils.hover(vp.infoAndVisiButton);
		Utils.waitFor(3);
		assertTrue(vp.interactiveGallery.isDisplayed(), "Interactive Gallery shoulb be visible");

	}

	// Check if "Interactive Gallery" link is clickable TC15
	@Test
	public void interactiveGalleryClickable() {
		gotoVisualizationPage();
		Utils.waitFor(3);
		Utils.clickJSE(vp.interactiveGallery);
		String actual = Driver.getDriver().getTitle();
		String expected = "Census Interactive Gallery";
		assertEquals(actual, expected);

	}

	// Number configuration "TC19
	// Verify that years are in decending order
	@Test
	public void verifyYears() {
		gotoVisualizationPage();
		Utils.hover(vp.yearsDropDownButton);
		Utils.clickJSE(vp.yearsDropDownButton);
		Utils.waitFor(3);
		Select select = new Select(Driver.getDriver().findElement(By.xpath("//select[@id='listFilterSelect']")));
		List<WebElement> yearsElement = select.getOptions();
		List<Integer> years = Utils.getDropDownYears(vp.selectYear);
		assertTrue(Utils.isDescending(years));
		System.out.println(years);
	}

	@Test
	public void verifyLinkIV() {
		gotoVisualizationPage();
		String actual = Driver.getDriver().getCurrentUrl();
		String expected = "https://www.census.gov/library/visualizations.html";
		assertEquals(actual, expected);
	}

	// Verify Infographics & Visualizations page title TEST"TC005
	@Ignore
	@Test
	public void verifyTitleIV() {
		gotoVisualizationPage();
		String actual = Driver.getDriver().getTitle();
		String expected = "Census Infographics & Visualizations";
		assertEquals(actual, expected);
	}

	// Verify number of pages Infographics & Visualizations TEST"TC006

	@Test
	public void verifyPagesIV() {
		gotoVisualizationPage();
		String actualStr = vp.countPagesBlock.getText();
		int actual = Utils.getTotalPages(actualStr);
		int expected = 3;
		assertEquals(actual, expected);
	}

	// Verify Next Page Link Clickable TEST"TC007

	@Test
	public void nextPageClickable() {
		gotoVisualizationPage();
		assertTrue(vp.nextPage.isEnabled());
		Utils.clickJSE(vp.nextPage);
		Utils.waitFor(5);
		String actualStr = vp.countPagesBlock.getText();
		System.out.println(actualStr + " after clicking next");
		int actual = Utils.getCurrentPage(actualStr);
		int expected = 2;
		assertEquals(actual, expected);
	}

	// Verify Previous Page Link Clickable"TC008

	@Test
	public void previousPage() {
		gotoVisualizationPage();
		assertTrue(vp.nextPage.isEnabled());
		Utils.clickJSE(vp.nextPage);
		Utils.waitFor(5);
		String actualStr = vp.countPagesBlock.getText();
		System.out.println(actualStr + " after clicking next");
		int actual1 = Utils.getCurrentPage(actualStr);
		int expected1 = 2;
		assertEquals(actual1, expected1);
		assertTrue(vp.prevPage.isEnabled());
		Utils.clickJSE(vp.prevPage);
		Utils.waitFor(5);
		String actualStr2 = vp.currentPage.getText();
		System.out.println(actualStr2 + " after clicking previous");
		int actual2 = Integer.parseInt(actualStr2);
		int expected2 = 1;
		assertEquals(actual2, expected2, "going to 1");

	}

	// Verify number of pages Infographics & Visualizations for 2017''TC009

	@Test
	public void totalPages2017() {
		gotoVisualizationPage();
		Utils.clickJSE(vp.year2017);
		String actualStr = vp.countPagesBlock.getText();
		int actual = Utils.getTotalPages(actualStr);
		int expected = 6;
		assertEquals(actual, expected);

	}

	// Verify change in content when navigating to the next page for 2017"TC010
	@Test
	public void content2017() {
		gotoVisualizationPage();
		Utils.clickJSE(vp.year2017);
		assertEquals(vp.currentPageCount.getText(), "1");
		String actual = vp.censusNavigator.getText().trim();
		System.out.println(actual);
		String expected = "Census Engagement Navigator";
		assertEquals(actual, expected);
	}

	// Verify dropdown functionality of Infographics and visualiation linkTC11
	@Test
	public void dropDown() {
		// 1step
		Utils.clickJSE(homePage.libraryLink);
		Utils.clickJSE(homePage.linkInfoAndVisualizations);
		assertTrue(!homePage.linkInfoAndVisualizations.isDisplayed());
		// 2step
		Utils.clickJSE(vp.dropDown);
		// 3 step
		Utils.clickJSE(vp.year2014);
		assertTrue(vp.year2014.isDisplayed());
	}

	// Interactive Gallery TC16
	@Test
		public void interActiveGalleryisNotVisible() {
			//1step
			Utils.clickJSE(homePage.libraryLink);
			//2 step
			Utils.clickJSE(vp.audioLink);
			assertTrue(vp.interActiveGallery.isEnabled());
	}
			
	// Verify content change according to a selection"TC12
	@Test
	public void changeOfContent() {
		gotoVisualizationPage();
		String actual = vp.contentOfPageText.getText();
		String expected = "Infographics & Visualizations";
		assertEquals(actual, expected);
		Utils.clickJSE(vp.year2016);
		Utils.clickJSE(vp.happyNewYear2017);
		String actual2 = vp.contentOfPageText.getText();
		String expected2 = "Happy New Year 2017";
		assertEquals(actual2, expected2);
	}

	// Verify number of pages for all years on Infographics & Visualizations
	// page"TC13
	@Test // from test NG
	public void totalPages() {
		gotoVisualizationPage();
		Utils.clickJSE(vp.allYears);
		String actualStr = vp.countPagesBlock.getText();
		int actual = Utils.getTotalPages(actualStr);
		int expected = 29;
		assertEquals(actual, expected);
	}

	// Checking social media icons"TC14
	@Test
	public void checkSocialIcons() {
		gotoVisualizationPage();
		vp.clickOnRandomImage();
		Utils.waitFor(3);
		assertTrue(vp.facebook.isDisplayed());
		assertTrue(vp.pinterest.isDisplayed());
		assertTrue(vp.twitter.isDisplayed());
	}
	// DATE" TC18

	@Test
	public void verifyData() {
		gotoVisualizationPage();
		Utils.waitFor(2);
		vp.clickOnRandomYear();
		vp.clickOnRandomImage();
		Utils.waitFor(3);
		assertTrue(vp.currectPageDate.isDisplayed());
		assertTrue(vp.currectPicData.isDisplayed());

	}

}
