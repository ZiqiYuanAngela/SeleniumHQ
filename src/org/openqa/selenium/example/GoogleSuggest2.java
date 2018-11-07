/*
Author: Ziqi Yuan
Last Time Modified: 11/06/18
*/

package org.openqa.selenium.example;

import java.util.ArrayList;
import java.util.List;
//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
public class GoogleSuggest2 {

	public static void main(String[] args) {
		
        System.setProperty("webdriver.chrome.driver", "src/org/openqa/selenium/example/chromedriver");
        System.setProperty("webdriver.chrome.silentLogging", "true");
        WebDriver driver=new ChromeDriver();
        //driver.get("http://www.google.com/webhp?complete=1&hl=en");
        driver.get("https://www.wikipedia.org/");
		//WebElement query =driver.findElement(By.name("q"));
		//query.sendKeys("Cheese");
		//query.submit();
        driver.findElement(By.name("search")).sendKeys("Selenium");
        
		
		//Sleep until the div we want is visible or 5 seconds is over
		long end=System.currentTimeMillis()+5000;
		while(System.currentTimeMillis()<end) {
			//findElement would throw a NoSuchElement Exception if the element is not present. Using findElements is 
			//a better approach
			ArrayList<WebElement> resultsDiv=(ArrayList<WebElement>)driver.findElements(By.className("typeahead-suggestions"));
			//If results have been returned, the results are displayed in a drop down
			if(resultsDiv.size()>0) {
				break;
			}
		}
		//driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		//And now list the suggestions
		//List<WebElement> allSuggestions =driver.findElements(By.className("sbct"));
		List<WebElement> allSuggestions =driver.findElements(By.xpath("//*[@id=\"typeahead-suggestions\"]"));
		System.out.println(allSuggestions.size());

		Actions action=new Actions(driver);
		for(WebElement suggestion: allSuggestions) {
			System.out.println(suggestion.getText());
			if(suggestion.getText().contains("software")) {
				suggestion.click();
			}
			
			
		}
		driver.close();
	}

}
