package selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import bsh.Console;

public class SuratCovidHospitalData {
	public static void collectDataFromWebsite(WebDriver driver, String Zonename) throws InterruptedException {	
		int noOfHospitals=driver.findElements(By.xpath("//div[@class='card custom-card']")).size();
		for(int i=1;i<=noOfHospitals;i++)
		{
			System.out.println("------------------------------------------------------");
			System.out.println("Zone name        :: " + Zonename);
			WebElement hospName = driver.findElement(By.xpath("//div[@class='card custom-card']["+i+"]//a")); 
			System.out.println("Name of Hospital :: " + hospName.getText().replace("Contact",""));

			WebElement totalbeds = driver.findElement(By.xpath("//div[@id='headingOne-"+i+"']//span[contains(text(),'Total Beds')]"));
			System.out.println("Total beds       :: " + totalbeds.getText());
			WebElement Vacancies = driver.findElement(By.xpath("//div[@id='headingOne-"+i+"']//span[contains(text(),'Total Vacant')]"));
			System.out.println("Total vacancies  :: " + Vacancies.getText());
			//WebDriverWait(driver,20).until(EC.element_to_be_clickable((By.xpath, "//div[@id='headingOne-"+i+"']"))).click();
			driver.findElement(By.xpath("//div[@id='headingOne-"+i+"']")).click();
			Thread.sleep(500);
			WebElement Ventilator=driver.findElement(By.xpath("//div[@id='collapseOne-"+i+"']//div[text()='Ventilator']/../div[2]"));
			System.out.println("Ventilators      :: " + Ventilator.getText());
			WebElement O2=driver.findElement(By.xpath("//div[@id='collapseOne-"+i+"']//div[text()='HDU(O2)']/../div[2]"));
			System.out.println("O2 beds          :: " + O2.getText());
			hospName.click();
			Thread.sleep(500);

			WebElement Address=driver.findElement(By.id("lblhosname"));
			//System.out.println(driver.findElements(By.id("lblhosname")).size());
			System.out.println("Address          :: " + (Address.getText().length() == 0? "N/A":Address.getText()));
			WebElement Contact=driver.findElement(By.id("lblhosCno"));
			System.out.println("Contact          :: " + (Contact.getText().length() == 0? "N/A":Contact.getText()));
			try {
				if(driver.findElements(By.xpath("//button[@class='close']/span")).size() > 0)
					driver.findElement(By.xpath("//button[@class='close']/span")).click();
			} catch(Exception ex) {

			}
			Thread.sleep(500);
		}
	}

	public static void PrintZoneHospitals(String printOption) throws InterruptedException {
		String path=System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path+"/src/test/resources/Drivers/chromedriver.exe");	
		WebDriver driver = new ChromeDriver();
		driver.get("http://office.suratsmartcity.com/SuratCOVID19/Home/COVID19BedAvailabilitydetails");
		Select zones=new Select(driver.findElement(By.id("ddlZone")));
		List <WebElement> zoneOptions=zones.getOptions();
		int i = 1;
		int noOfZones=zoneOptions.size();
		
		if(printOption == "ALL") {
			i = 0;
			noOfZones = 1;
		}
		for(;i<noOfZones;i++) //Index starts with 0
		{
			//Selecting the zone
			Select zones1=new Select(driver.findElement(By.id("ddlZone")));	
			zones1.selectByIndex(i);
			
			//Reading selected zone name
			Select zones2=new Select(driver.findElement(By.id("ddlZone")));	
			collectDataFromWebsite(driver, zones2.getFirstSelectedOption().getText());		
			
			Thread.sleep(2000);
		}
		driver.close();
	}

	public static void main(String[] args) throws InterruptedException {
		//collectDataFromWebsite();
		//java.io.Console console = System.console();
		String printOption = "ALL";//console.readLine();
		PrintZoneHospitals(printOption);
		
		//PrintZoneHospitals("ZONEWISE");
		
	}


}




