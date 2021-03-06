/*
Ahthor:Ziqi Yuan
Last Time Modified: 11/06/18
*/

package org.openqa.selenium.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WikiSuggest0 {

	public static void main(String[] args) throws IOException {
		//The Firefox driver supports javascript
		System.setProperty("webdriver.gecko.driver", "src/org/openqa/selenium/example/geckodriver");
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"src/org/openqa/selenium/example/output");
		FirefoxOptions capabilities=new FirefoxOptions();
		capabilities.setCapability("marionette", true);
		
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		//Go to the Google Suggest home page
		//driver.get("http://www.google.com/webhp?complete=1&hl=en");
		driver.get("https://en.wikipedia.org/wiki/Main_Page");
		
		//Enter the query string "Cheese"
		WebElement query =driver.findElement(By.name("search"));
		//query.sendKeys("Cheese");
		query.sendKeys("Selenium");
		/*query.sendKeys("S");
		query.sendKeys("e");
		query.sendKeys("l");
		query.sendKeys("e");
		query.sendKeys("n");
		query.sendKeys("i");
		query.sendKeys("u");
		query.sendKeys("m");*/
		//Sleep until the div we want is visible or 5 seconds is over
		long end=System.currentTimeMillis()+5000;
		while(System.currentTimeMillis()<end) {
			//findElement would throw a NoSuchElement Exception if the element is not present. Using findElements is 
			//a better approach
			ArrayList<WebElement> resultsDiv=(ArrayList<WebElement>)driver.findElements(By.className("sbsb_a"));
			//If results have been returned, the results are displayed in a drop down
			
			if(resultsDiv.size()>0) {
				break;
			}
		}
		
		//And now list the suggestions
		List<WebElement> allSuggestions =driver.findElements(By.className("suggestions-result"));
		System.out.println(allSuggestions.size());
		
		for(WebElement suggestion: allSuggestions) {
			System.out.println(suggestion.getText());
			
		}

		for(WebElement suggestion: allSuggestions) {
            if(suggestion.getText().contains("software")) {
            	suggestion.click();
            	break;
            }
			
		}
		driver.quit();
		
	}

}
