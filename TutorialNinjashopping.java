package selenium;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

@Test
public class TutorialNinjashopping {

	static WebDriver driver;
	static {
		System.out.println("static block");
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "/src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Launch the application http://tutorialsninja.com/demo/index.php
		driver.get("http://tutorialsninja.com/demo/index.php ");
	}

	public static void orderclick() throws InterruptedException {
		try {
			//Select the currency in the left top corner to Euro
			Actions actions = new Actions(driver);
			WebElement we = driver.findElement(By.xpath("//div/button"));
			actions.moveToElement(we).click().build().perform();
			List<WebElement> currencylist = driver.findElements(By.xpath("//div//li"));
			List<String> currencyNames = new ArrayList<String>();
			WebElement Eurobutton = driver.findElement(By.xpath("//div//li/button"));

			for (WebElement currency : currencylist) {
				currencyNames.add(currency.getText());
			}
			for (String currencyName : currencyNames) {
				if (currencyName.equals("€ Euro")) {
					Eurobutton.click();
				}
			}
           //Try to order a canon EOS 5 D camera  
			driver.findElement(By.xpath("//div[@id='search']/input")).sendKeys("canon EOS 5D" + Keys.ENTER);
			driver.findElement(By.xpath("//div[@id='search']//span/button")).click();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,1000)");
			driver.findElement(By.xpath("//div[@class='button-group']/button")).click();
			Thread.sleep(500);
			WebElement addtocart = driver.findElement(By.xpath("//div[@class='form-group']//button"));
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("window.scrollBy(0,200)");
			addtocart.click();
			Thread.sleep(500);
			//collect the error message occurred due to a bug in select option
			WebElement Errormessage = driver.findElement(By.xpath("//div[@id='product']//div[@class='text-danger']"));
			System.out.println("Error message is::" + Errormessage.getText());
			//Move to the home screen, Click on iphone  		
			driver.findElement(By.xpath("//ul[@class='breadcrumb']//a")).click();
			Thread.sleep(500);
			JavascriptExecutor iphn = (JavascriptExecutor) driver;
			iphn.executeScript("window.scrollBy(0,200)");
			Thread.sleep(500);
			//go to details screen		
			driver.findElement(By.xpath("//div[@id='common-home']//div[@id='content']/div[@class='row']/div[2]")).click();
			Thread.sleep(500);
			//change the quantity to two then add to cart	
			driver.findElement(By.xpath("//label[text()='Qty']/../input[1]")).sendKeys(Keys.CONTROL, "a", Keys.DELETE);
			driver.findElement(By.xpath("//label[text()='Qty']/../input[1]")).sendKeys("2" + Keys.ENTER);
			driver.findElement(By.xpath("//div[@class='form-group']//button")).click();
			// Print the success message in the console
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success alert-dismissible']")));
			System.out.println("Success message::" + msg.getText().replace("×",""));
            // Click on the cart icon (black color) in the right side top then click view cart
			driver.findElement(By.xpath("//div[@id='cart']/button")).click();
			driver.findElement(By.xpath("//p[@class='text-right']/a[1]/strong")).click();
          // Change the quantity of iphone to 3 
			driver.findElement(By.xpath("//div[@class='input-group btn-block']/input")).sendKeys(Keys.CONTROL, "a",Keys.DELETE);
			driver.findElement(By.xpath("//div[@class='input-group btn-block']/input")).sendKeys("3" + Keys.ENTER);
			//  click update button
			driver.findElement(By.xpath("//div[@class='input-group btn-block']//button[1]")).click();
			JavascriptExecutor tax = (JavascriptExecutor) driver;
			tax.executeScript("window.scrollBy(0,1000)");
			// printing VAT and ECO
			WebElement ECO1 = driver.findElement(By.xpath("//div[@class='col-sm-4 col-sm-offset-8']/table[@class='table table-bordered']//tr[2]//td[1]"));
			WebElement ECO2 = driver.findElement(By.xpath("//div[@class='col-sm-4 col-sm-offset-8']/table[@class='table table-bordered']//tr[2]//td[2]"));
			System.out.println("Eco tax is ::" + ECO1.getText() + ECO2.getText().replace("€","\u20AC"));
			WebElement VAT1 = driver.findElement(By.xpath("//div[@class='col-sm-4 col-sm-offset-8']/table[@class='table table-bordered']//tr[3]//td[1]"));
			WebElement VAT2 = driver.findElement(By.xpath("//div[@class='col-sm-4 col-sm-offset-8']/table[@class='table table-bordered']//tr[3]//td[2]"));
			System.out.println("VAT is ::"+ VAT1.getText() + VAT2.getText().replace("€","\u20AC"));
			// click checkout
			driver.findElement(By.xpath("//div[@class='buttons clearfix']//div[2]/a")).click();
			// print error message
			WebElement errmsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
			System.out.println("Error message is ::  " + errmsg.getText().replace("×",""));
			// remove product from cart
			driver.findElement(By.xpath("//div[@class='input-group btn-block']//button[2]")).click();
			Thread.sleep(1000);
			// move to home screen
			driver.findElement(By.xpath("//ul[@class='breadcrumb']//a")).click();
			// click on mac book
			driver.findElement(By.xpath("//div[@id='common-home']//div[@id='content']/div[@class='row']/div[1]")).click();
			// check if quantity is 1
			WebElement macbookqty = driver.findElement(By.xpath("//label[text()='Qty']/../input[1]"));
			String expmacbookqty = "1";
			if (macbookqty.getText().equals(expmacbookqty)) {
				System.out.println("Quantity OF MAC Book is:"+macbookqty.getText());
			}
			driver.findElement(By.xpath("//div[@class='form-group']//button")).click();
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement macbookmsg = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success alert-dismissible']")));
			System.out.println("Success message::"+macbookmsg.getText().replace("×",""));
			// click on shopping cart link
			driver.findElement(By.xpath("//ul[@class='list-inline']//li[4]/a")).click();
			// click on couponcode
			WebElement couponclick=new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Use Coupon Code")));
			couponclick.click();
			// enter coupon code
			//Thread.sleep(500);
			WebElement coupon=new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='collapse-coupon']//input[@name='coupon']")));
			coupon.sendKeys("ABCD123" + Keys.ENTER);
			// click apply couponcode
			driver.findElement(By.xpath("//span[@class='input-group-btn']/input[@id='button-coupon']")).click();
			// collect error message
			WebElement gifterrormsg = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger alert-dismissible']")));
			System.out.println("Error message is ::" + gifterrormsg.getText().replace("×",""));
			 //click gift certificate
			Thread.sleep(500);
			 ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)");
			 WebElement giftcert=new WebDriverWait(driver,Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Use Gift Certificate")));
			 giftcert.click();
			 //enter gift certificate 
			 driver.findElement(By.xpath( "//div[@id='collapse-voucher']//input[@name='voucher']")).sendKeys("AXDFGH123" + Keys.ENTER);
			 //click apply voucher
			 driver.findElement(By.xpath( "//span[@class='input-group-btn']/input[@id='button-voucher']")).click();
			 //collect error message
			 WebElement giftmsg=new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='checkout-cart']//div[contains(text(),'Warning: Gift Certificate is either invalid')]"))); 
			  System.out.println("gift certificate message is ::"+giftmsg.getText().replace("×",""));		
		  // clear both textbox values
			 ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)");
			Thread.sleep(1000);
			WebElement erasegiftcode=new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//div[@id='collapse-voucher']//input[@name='voucher']")));
			erasegiftcode.clear();
			WebElement couponcode=new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Use Coupon Code")));
			couponcode.click();
			WebElement couponclear=new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.id("input-coupon")));
			couponclear.clear();		 
			  //proceed to checkout
			((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)");
              driver.findElement(By. xpath("//div[@class='buttons clearfix']//a[text()='Checkout']")).click();
			 //select register account option and click continue
              Thread.sleep(500);
			 driver.findElement(By.xpath("//input[@id='button-account']")).click();
			 Thread.sleep(500);
			 driver.findElement(By.id("input-payment-firstname")).sendKeys("Swetha"+Keys.ENTER);
			 driver.findElement(By.id("input-payment-lastname")).sendKeys("Chinni"+Keys.ENTER);
			 driver.findElement(By.id("input-payment-email")).sendKeys("cfkczwqf@gmail.com"+Keys.ENTER);
			 driver.findElement(By.id("input-payment-telephone")).sendKeys("3467822209"+Keys.ENTER);
			 driver.findElement(By.id("input-payment-password")).sendKeys("chinni"+Keys.ENTER);
			 driver.findElement(By.id("input-payment-confirm")).sendKeys("chinni"+Keys.ENTER);
			 driver.findElement(By.id("input-payment-address-1")).sendKeys("13467"+Keys.ENTER);
			 driver.findElement(By.id("input-payment-city")).sendKeys("Tustin"+Keys.ENTER);
			 driver.findElement(By.id("input-payment-postcode")).sendKeys("94567"+Keys.ENTER);
			 Select country=new Select(driver.findElement(By.id("input-payment-country")));			 
			 country.selectByValue("223");
			 Thread.sleep(2000);
			 Select state=new Select(driver.findElement(By.id("input-payment-zone")));
			 state.selectByValue("3624");
			 //agree to terms and continue
			 driver.findElement(By.xpath("//input[@name='agree']")).click();
			 driver.findElement(By.xpath("//input[@id='button-register']")).click();
			 //Add comments, click continue and check the error message related to payment method
			 Thread.sleep(2000);
			 driver.findElement(By.xpath("//textarea[@class='form-control']")).sendKeys("Swetha's cart"+Keys.ENTER);
			driver.findElement(By.name("agree")).click();
			driver.findElement(By.id("button-payment-method")).click();
			 Thread.sleep(2000);
			 WebElement payerror=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
			 System.out.println(payerror.getText().replace("×",""));
			 //Click on contact us hyperlink and submit a contact request and click continue
			 JavascriptExecutor cont = (JavascriptExecutor) driver;
				cont.executeScript("window.scrollBy(0,1000)");
				driver.findElement(By.partialLinkText("Contact Us")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//textarea[@class='form-control']")).sendKeys("Thank you!!"+Keys.ENTER);
				driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
				driver.findElement(By.partialLinkText("Continue")).click();

		} catch (Exception ex) {
			System.out.println("Order Details: " + ex);
		}

	}
	public static void main(String[] args) throws Exception {
		orderclick();
	}
}
