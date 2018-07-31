package gov.census.utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import gov.census.pages.HomePage;
import gov.census.utilities.Driver;

public class Utils {

public static List<String> getStringValueOfAllElements(List<WebElement> list) {
		List<String> listStr = new ArrayList<>();
		for (WebElement el : list) {
			listStr.add(el.getText().trim());
		}		
		return listStr;
	}
	
	
	
	
	public static void waitFor(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void switchToWindow(String targetTitle) {
		String origin = Driver.getDriver().getWindowHandle();
		for (String handle : Driver.getDriver().getWindowHandles()) {
			Driver.getDriver().switchTo().window(handle);
			if (Driver.getDriver().getTitle().equals(targetTitle)) {
				return;
			}
		}
		Driver.getDriver().switchTo().window(origin);
	}

	public static int getTotalPages(String str) {
		// Page 1 of 6 >
		String digits = str.replaceAll("[^0-9]", "");
		return Integer.parseInt(digits.substring(1));
	}

	public static boolean isDescending(List<Integer> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).compareTo(list.get(i + 1)) < 0) {
				return false;
			}
		}
		return true;
	}

	public static List<Integer> getDropDownYears(WebElement selectYear) {
		List<Integer> years = new ArrayList<>();
		Select select = new Select(selectYear);
		List<WebElement> yearsElement = select.getOptions();
		for (int i = 0; i < yearsElement.size(); i++) {
			String yearInfo = yearsElement.get(i).getAttribute("value").trim();
			String year = yearInfo.replaceAll("[^0-9]", "");
			if (year.isEmpty())
				continue;

			years.add(Integer.parseInt(year));
		}

		return years;

	}

	public static int getCurrentPage(String str) {
		// Page 1 of 6 >
		String digits = str.replaceAll("[^0-9]", "");
		return Integer.parseInt(digits.substring(0, 1));
	}

	public static void clickJSE(WebElement e) {
		JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
		jse.executeScript("arguments[0].click();", e);
	}

	public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static List<WebElement> waitForVisibility(List<WebElement> elements, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
		return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public static void hover(WebElement element) {
		Actions actions = new Actions(Driver.getDriver());
		actions.moveToElement(element).perform();
	}

}
