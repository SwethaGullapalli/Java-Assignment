package testSuite1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium101 {
	public static void main(String[] args) {
		String path=System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path+"/src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://cosmocode.io/automation-practice/");
		driver.findElement(By.id("firstname")).sendKeys(" Swetha");
		driver.findElement(By.className("lastname")).sendKeys("G");
		//driver.findElement(By.xpath("//form[@id='searchbox']/input[4]")).sendKeys("");
		driver.findElement(By.xpath("//input[@value='Male']")).click();
		driver.findElement(By.xpath("//input[@value='Female']")).click();
		driver.findElement(By.name("language_java")).click();
		driver.findElement(By.name("language_python")).click();
		//driver.findElement(By.name("Age"));
		driver.findElement(By.xpath("//input[@id='submit_htmlform']")).click();
		//driver.findElement(By.xpath("//label[text()='First Name']"));
		//driver.findElement(By.xpath("//input[@type='submit' and @value='Submit']")).click();
		//driver.findElement(By.linkText("random-text-xyz-i-wont-change-random-digit-123")).click();
		//driver.findElement(By.linkText("Click Me to open New Window")).click();
		//driver.findElement(By.partialLinkText("submit_htmlform")).submit();
		}
}
		


