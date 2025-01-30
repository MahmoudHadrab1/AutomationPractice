package MyTestCases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MainTestCases {
	WebDriver driver = new ChromeDriver();

	String WebSite = "https://codenboxautomationlab.com/practice/";

	Random rand = new Random();

	JavascriptExecutor js = (JavascriptExecutor) driver;

	
	Actions action = new Actions(driver);

	@BeforeTest
	public void mySetup() {

		driver.manage().window().maximize();
		driver.get(WebSite);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test(priority = 1, description = "Radio button", invocationCount = 1, enabled = false)

	public void Radio_Button_Example() throws InterruptedException {

		List<WebElement> AllRadioButtons = driver.findElements(By.className("radioButton"));


		int randomIndex = rand.nextInt(AllRadioButtons.size()); 
		AllRadioButtons.get(randomIndex).click();

	
		boolean expectedddddddddddResult = true;
		boolean ActualResult = AllRadioButtons.get(randomIndex).isSelected();

		Assert.assertEquals(ActualResult, expectedddddddddddResult);



		for (int i = 0; i < AllRadioButtons.size(); i++) {
			Thread.sleep(1000);
			AllRadioButtons.get(i).click();
		}
	}

	@Test(priority = 2, description = "dropDown Dynamic", enabled = false)
	public void Dynamic_Dropdown_Example() throws InterruptedException {

		String[] countryCodes = { "US", "CA", "OM", "BR", "AR", "FR", "DE", "IT", "ES", "AM" };

	

		int randomIndex = rand.nextInt(countryCodes.length);


		WebElement DynamicListInput = driver.findElement(By.id("autocomplete"));

		
		DynamicListInput.sendKeys(countryCodes[randomIndex]);

		Thread.sleep(1000);

	
		DynamicListInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));


		String DataInsideMyInput = (String) js.executeScript("return arguments[0].value", DynamicListInput);

	
		String updateDataInMyInput = DataInsideMyInput.toUpperCase();

		boolean ActualValue = updateDataInMyInput.contains(countryCodes[randomIndex].toUpperCase());

		boolean ExpectedResult = true;

		Assert.assertEquals(ActualValue, ExpectedResult);

	}

	@Test(priority = 3, description = "static dropdown list", enabled = false)
	public void Static_Dropdown_Example() {
		WebElement SelectElement = driver.findElement(By.id("dropdown-class-example"));
		Select sel = new Select(SelectElement);

		sel.selectByIndex(3);
	
	}

	@Test(priority = 4, description = "check box example", enabled = false)
	public void Checkbox_Example() throws InterruptedException {
		List<WebElement> CheckBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		int randomIndex = rand.nextInt(CheckBoxes.size());
		System.out.println(CheckBoxes.size());
		Thread.sleep(1000);
	
		for (int i = 0; i < CheckBoxes.size(); i++) {
			CheckBoxes.get(i).click();
			boolean ActaualResult = CheckBoxes.get(i).isSelected();
			boolean expectedResult = true;

			Assert.assertEquals(ActaualResult, expectedResult);

		}

	}

	@Test(priority = 5, description = "this is to move from window to another one", enabled = false)
	public void Switch_Window_Example() throws InterruptedException {

		WebElement OpenWindowButton = driver.findElement(By.id("openwindow"));
		OpenWindowButton.click();
		Thread.sleep(2000);
		List<String> windowsHandels = new ArrayList<String>(driver.getWindowHandles());
	
		System.out.println(windowsHandels.size());

		driver.switchTo().window(windowsHandels.get(1));
	
		WebElement ContactButton = driver.findElement(By.id("menu-item-9680"));
		ContactButton.click();

		System.out.println(driver.getTitle() + " hello from the second window");

		driver.close();
	
		driver.switchTo().window(windowsHandels.get(0));

	}

	@Test(priority = 6, description = "check moving to another tab", enabled = false)

	public void Switch_Tab_Example() throws InterruptedException {
		WebElement OpenTabButton = driver.findElement(By.id("opentab"));

		OpenTabButton.click();

		List<String> windowsHandels = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowsHandels.get(1));
		Thread.sleep(2000);
		System.out.println(driver.getTitle());
	}

	@Test(priority = 7, description = "Alert and confirm", enabled = false)
	public void Switch_To_Alert_Example() throws InterruptedException {
		WebElement nameBox = driver.findElement(By.id("name"));
		nameBox.sendKeys("abedalraheem");
	

		Thread.sleep(1000);
	

		WebElement ConfirmBox = driver.findElement(By.id("confirmbtn"));
		ConfirmBox.click();
		Thread.sleep(1000);
		
	}

	@Test(priority = 8, description = " play with the data of the column ", enabled = false)

	public void Web_Table_Example() {

		WebElement TheTable = driver.findElement(By.id("product"));

		List<WebElement> theDataInsideTheTable = TheTable.findElements(By.tagName("tr"));

		for (int i = 1; i < theDataInsideTheTable.size(); i++) {

			int totalTdInTheRow = theDataInsideTheTable.get(i).findElements(By.tagName("td")).size();

			System.out.println(
					theDataInsideTheTable.get(i).findElements(By.tagName("td")).get(totalTdInTheRow - 1).getText());
		}

	}

	@Test(priority = 9, description = "this is to test hide and show buttons", enabled = false)
	public void Element_Displayed_Example() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert myAssertion = new SoftAssert();

	

		js.executeScript("window.scrollTo(0,1500)");

		WebElement HideButton = driver.findElement(By.id("hide-textbox"));
		WebElement ShowButton = driver.findElement(By.id("show-textbox"));

		HideButton.click();

		WebElement theTEXXXXXTINPUT = driver.findElement(By.id("displayed-text"));

		

		myAssertion.assertEquals(theTEXXXXXTINPUT.isDisplayed(), false);

		Thread.sleep(4000);
		ShowButton.click();
		Assert.assertEquals(theTEXXXXXTINPUT.isDisplayed(), true);

		myAssertion.assertAll();
	}

	@Test(priority = 10, description = "check The Both Buttons disable , enable", enabled = false)
	public void Enabled_Disabled_Example() throws InterruptedException {
		WebElement DisabledButton = driver.findElement(By.id("disabled-button"));
		WebElement EnabledButton = driver.findElement(By.id("enabled-button"));

		DisabledButton.click();

		WebElement enabled_example_input = driver.findElement(By.id("enabled-example-input"));

		boolean ActualResult = enabled_example_input.isEnabled();

		boolean ExpectedResult = false;

		Assert.assertEquals(ActualResult, ExpectedResult);

		Thread.sleep(1000);

		EnabledButton.click();
		boolean ActualResult2 = enabled_example_input.isEnabled();
		enabled_example_input.sendKeys("123");
		boolean ExpectedResult2 = true;
		Assert.assertEquals(ActualResult2, ExpectedResult2);

	}

	@Test(priority = 11, description = "check the hover to certain element", enabled = false)
	public void Mouse_Hover_Example() throws InterruptedException {

		js.executeScript("window.scrollTo(0,1800)");
		Thread.sleep(2000);

		WebElement MouseHoverElement = driver.findElement(By.id("mousehover"));

		action.moveToElement(MouseHoverElement).perform();
		;

		Thread.sleep(1000);
	
		driver.findElement(By.partialLinkText("Relo")).click();

	}

	@Test(priority = 12, description = "open calendar in a new tab", enabled = false)
	public void Calendar_Example() throws InterruptedException {
	
		js.executeScript("window.scrollTo(0,1900)");

	
		WebElement Calendar2 = driver.findElement(By.partialLinkText("Booking"));

		Calendar2.click();
		Thread.sleep(1000);

		List<String> windowsHandels = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowsHandels.get(1));

		System.out.println(driver.getTitle());

		int totalAvailbleDates = driver.findElements(By.className("date_available")).size();

		driver.findElements(By.className("date_available")).get(0).click();
		driver.findElements(By.className("date_available")).get(totalAvailbleDates - 1).click();

	}

	@Test(priority = 13, description = "switch to frame inside the main page", enabled = false)
	public void iFrame_Example() {

		WebElement TheFrame = driver.findElement(By.id("courses-iframe"));
		
		driver.switchTo().frame(0);
	
		driver.switchTo().frame("courses-iframe");
		
		driver.switchTo().frame(TheFrame);

		String theText = driver.findElement(By.xpath("//*[@id=\"ct_text_editor-be8c5ad\"]/div/div/p")).getText();

		System.out.println(theText);

	}

	@Test(priority = 14, description = "download the file inside the main page", enabled = false)
	public void Download_file_to_test() {
		
		
		WebElement TheFile = driver.findElement(By.xpath("//a[@class='wp-block-button__link wp-element-button']"));

		TheFile.click();
	}

	@Test(priority = 15, enabled = false)

	public void CheckTheTitle() {
		String expected = "Automation Practice - CodenBox AutomationLab";

		String ActualTitle = driver.getTitle();

		Assert.assertEquals(ActualTitle, expected);
	}

}

