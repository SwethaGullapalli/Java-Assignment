package selenium;

import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WestbengalCovidHospitalData {
	private static final String String = null;
	private static Object async;


	/**
	 * @throws InterruptedException
	 */
	public static void collectDataFromWebsite(WebDriver driver, String Area) throws InterruptedException {
	   
		//String path=System.getProperty("user.dir");
		//System.setProperty("webdriver.chrome.driver", path+"/src/test/resources/Drivers/chromedriver.exe");	
		//WebDriver driver = new ChromeDriver();
		//driver.manage().window().maximize();
		//driver.get("https://westbengal.covidsafe.in/");
		try {
			Thread.sleep(2000);
			int noOfHospitals=driver.findElements(By.xpath("//tbody/tr")).size();
			//System.out.println("No of Hospitals: " + noOfHospitals);
			for(int i=1;i<=noOfHospitals;i++)
			{
				Thread.sleep(500);
				System.out.println("------------------------------------------------------");
				System.out.println("------------------------------------------------------");
				System.out.println("Area name        :: " +Area);
				WebElement hospName = driver.findElement(By.xpath("//tbody/tr["+i+"]//strong"));
				//Actions actions = new Actions(driver);
				//actions.moveToElement(hospName);
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].scrollIntoView()", hospName); 
				System.out.println( i + " - Name of Hospital :: " + hospName.getText());
				
				WebElement withoutoxygen=driver.findElement(By.xpath("//tbody/tr["+i+"]//td[2]")); 
				
				System.out.println("     Beds without Oxgen :: "+withoutoxygen.getText()); 
				
				WebElement withoxygen=driver.findElement(By.xpath("//tbody/tr["+i+"]//td[3]")); 
				
				System.out.println("     Beds with oxygen :: "+withoxygen.getText()); 
				
				WebElement withoutventilator=driver.findElement(By.xpath("//tbody/tr["+i+"]//td[4]"));
				
				System.out.println("     Beds without ventilator :: "+withoutventilator.getText()); 
			 
				WebElement withventilator=driver.findElement(By.xpath("//tbody/tr["+i+"]//td[5]")); 
				
				System.out.println("     Beds with ventilator :: "+withventilator.getText());
				//actions = new Actions(driver);
				//actions.moveToElement(hospName).click().perform();
			    hospName.click();
				WebElement Address=driver.findElement(By.xpath("//tr[@class='border-bottom']//p[4]/span"));
				//JavascriptExecutor jse = (JavascriptExecutor)driver;
				//jse.executeScript("arguments[0].scrollIntoView()", Address); 
				WebElement Pincode=driver.findElement(By.xpath("//tr[@class='border-bottom']//p[3]/span"));
				System.out.println("     Address of Hospital ::"+Address.getText()+" ,"+Pincode.getText());
				WebElement Contact=driver.findElement(By.xpath("//tr[@class='border-bottom']//p[2]/span"));
				System.out.println("     Contact of Hospital ::"+Contact.getText());
				hospName.click();	
		    	if(i%20==0) {
			     WebElement loadhospitalsbutton=driver.findElement(By.xpath("//button[text()='Load next 20']"));	
				 JavascriptExecutor executor = (JavascriptExecutor)driver;
				 executor.executeScript("arguments[0].click();", loadhospitalsbutton);
				 Thread.sleep(1000);
				 noOfHospitals=driver.findElements(By.xpath("//tbody/tr")).size();
	
				}
				
			}
		}
		catch(Exception ex) {System.out.println("collectDataFromWebsite: " + ex);}
		//driver.close();
	}
	
		
	public static void PrintareawiseHospitals(WebDriver driver,List<WebElement> options,String value) throws InterruptedException {
		try {
			//System.out.println("Area Hospitals: " + value);
			for(WebElement area:options)
			{
				//System.out.println(area.getText() + " == " + value);
				if(area.getText().equals(value))
				{
					System.out.println("Area Matched: " + area.getText());
					//Thread.sleep(100);
					//jse.executeScript("scroll(0, 250)");
					//Actions actions = new Actions(driver);
					//actions.moveToElement(area).click().perform();
					JavascriptExecutor jse = (JavascriptExecutor)driver;
					jse.executeScript("window.scrollBy(0,500)"); 
				    area.click();
				    //collectDataFromWebsite(driver,value);
				 break;
				}
				
			}
		} catch(Exception ex) {System.out.println("PrintareawiseHospitals: " + ex);}
	}
			
		
		
		
	
		public static void main(String[] args) throws InterruptedException {
			
			//collectDataFromWebsite(null, null);
			
			String path=System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", path+"/src/test/resources/Drivers/chromedriver.exe");	
			WebDriver driver = new ChromeDriver();
			
			driver.manage().window().maximize();
			driver.get("https://westbengal.covidsafe.in/");
			Thread.sleep(1000);
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			WebElement Allareas=driver.findElement(By.xpath("//div[@class='dropdown']/button"));
			Allareas.click();
			Thread.sleep(1000);
			List<WebElement> area_menu_initial=driver.findElements(By.xpath("//div[@class='show dropdown']//a"));
			List<String> areaNames = new ArrayList<String>();
			for(WebElement area : area_menu_initial) {
				areaNames.add(area.getText());
			}
			Allareas.click();
			System.out.println(area_menu_initial.size());
			for(String areaName : areaNames) {
				if(areaName.equals("All Areas"))
					continue;
				try {
				//System.out.println(areaName);
				Allareas=driver.findElement(By.xpath("//div[@class='dropdown']/button"));
				
				Allareas.click();
				Thread.sleep(500);
				List<WebElement> area_menu=driver.findElements(By.xpath("//div[@class='show dropdown']//a"));
			    PrintareawiseHospitals(driver,area_menu, areaName);
				} catch(Exception ex) {}
			   /*
				
				PrintareawiseHospitals(driver,area_menu,"Bankura");
				area_menu=driver.findElements(By.xpath("//div[@class='show dropdown']//a"));
				PrintareawiseHospitals(driver,area_menu,"Birbhum");
				PrintareawiseHospitals(driver,area_menu,"Coochbehar");
				PrintareawiseHospitals(driver,area_menu,"Dakshin 24 Pargana");
				PrintareawiseHospitals(driver,area_menu,"Dakshin Dinajpur");
				PrintareawiseHospitals(driver,area_menu,"Darjeeling");
				PrintareawiseHospitals(driver,area_menu,"Hooghly");
				PrintareawiseHospitals(driver,area_menu,"Howrah");
				PrintareawiseHospitals(driver,area_menu,"Jalpaiguri");
				PrintareawiseHospitals(driver,area_menu,"Jhargram");
				PrintareawiseHospitals(driver,area_menu,"Kalimpong");
				PrintareawiseHospitals(driver,area_menu,"Kolkata Mc Area");
				PrintareawiseHospitals(driver,area_menu,"Malda");
				PrintareawiseHospitals(driver,area_menu,"Murshidabad");
				PrintareawiseHospitals(driver,area_menu,"Nadia");
				PrintareawiseHospitals(driver,area_menu,"Paschim Bardhaman");
				PrintareawiseHospitals(driver,area_menu,"Paschim Medinipur");
				PrintareawiseHospitals(driver,area_menu,"Purba Bardhaman");
				PrintareawiseHospitals(driver,area_menu,"Purba Medinipur");
				PrintareawiseHospitals(driver,area_menu,"Purulia");
				PrintareawiseHospitals(driver,area_menu,"Uttar 24 Pargana");
				PrintareawiseHospitals(driver,area_menu,"Uttar Dinajpur");
				PrintareawiseHospitals(driver,area_menu,"All areas");*/
			}
	    //driver.close();
	
		}
		//driver.close();
}

