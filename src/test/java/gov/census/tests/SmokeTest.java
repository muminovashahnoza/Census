package gov.census.tests;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import gov.census.pages.HomePage;
import gov.census.pages.VisualizationsPage;
import gov.census.utilities.Driver;
import gov.census.utilities.Utils;

public class SmokeTest extends TestBase{
	HomePage homePage = new HomePage();
	VisualizationsPage vPage = new VisualizationsPage();
	
	@Test
	public void smoke() {
		Utils.clickJSE(homePage.libraryLink);
		Utils.clickJSE(homePage.linkInfoAndVisualizations);
		Utils.clickJSE(vPage.year2015);
		List<WebElement> elementList = vPage.allPics;
		List<String> list = Utils.getStringValueOfAllElements(elementList);
		System.out.println(list);
		String expected = "World Statistics Day 2015";
		for (WebElement el : elementList) {
			if(el.getText().equals(expected)) {
				el.click();
				break;
			}  
		}
		String actual = Driver.getDriver().getTitle();
		assertEquals(expected, actual);
	}
   
}
