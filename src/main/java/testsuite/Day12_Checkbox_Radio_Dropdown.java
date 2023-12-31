package testsuite;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.common.CommonBase;

public class Day12_Checkbox_Radio_Dropdown extends CommonBase {	
	@BeforeTest
	@Parameters("browser")
    public void initDriver(@Optional("chrome") String browser) {
        setupDriver(browser);
        driver.get("https://demo.seleniumeasy.com/basic-radiobutton-demo.html");
    }
	
//	@BeforeTest
//    public void initDriverTest() {
//        driver = initEdgeDriver();
//        driver.get("https://demo.seleniumeasy.com/basic-radiobutton-demo.html");
//    }
	
	@Test
	public void isRadioButtonSelected() {
		//driver = initDriverTest("https://demo.seleniumeasy.com/basic-radiobutton-demo.html");
		// Tìm locator của Male checkbox và lưu vào Webelement
		WebElement checkboxMale=driver.findElement(By.xpath("(//input[@value='Male' and @type='radio'])[1]"));
		checkboxMale.click();
		// Kiểm tra checkbox Male đã được chọn hay chưa
		boolean isSelected = checkboxMale.isSelected();
		if(isSelected ==false)
		{
			// Nếu false tức là chưa chọn thì click chọn
			checkboxMale.click();
			System.out.println("Male radio button is selected");
		}
		else {
			WebElement checkboxFemale=driver.findElement(By.xpath("(//input[@value='Female' and @type='radio'])[1]"));
			// Nếu mà Male đã được check thì check vào Female còn lại
			// 1. Tìm locator của Female, 2. sau đó click vào Female, 3. in ra
			checkboxFemale.click();
			System.out.println("Female radio button is selected");
		}
	}
	
	/* Testcase: 
	1. vào trang dropdownlist https://demo.seleniumeasy.com/basic-select-dropdown-demo.html
	2. Tìm đến thẻ select là dropdownlist
	3. Tìm size của dropdownlist
	4. Click chọn Monday bằng cách 1, chọn Tuesday bằng cách 2, chọn Sunday bằng cách 3
	5. Sau khi chọn xong kiểm tra lại text đã đúng giá trị mình chọn hay chưa => Expected value: "Monday"
	*/

	public void selectDropdownlist() {		
		//driver = initDriverTest("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");
		Select dropDaySelect = new Select(driver.findElement(By.id("select-demo")));
		System.out.println("Size is: "+dropDaySelect.getOptions().size());
		WebElement dropDayWeblement = driver.findElement(By.id("select-demo"));
		dropDayWeblement.click();// Mở dropdownlist
		// Cách 1: dùng selectByVisibleText
		dropDaySelect.selectByVisibleText("Monday");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertEquals("Monday", dropDaySelect.getFirstSelectedOption().getText());
		// Cách 2: dùng selectByIndex
		dropDaySelect.selectByIndex(3);
		Assert.assertEquals("Tuesday", dropDaySelect.getFirstSelectedOption().getText());
		// Cách 3: dùng selectByValue
		dropDaySelect.selectByValue("Sunday");
		Assert.assertEquals("Sunday", dropDaySelect.getFirstSelectedOption().getText());
		dropDayWeblement.click(); // Đóng dropdownlist
	}
	@AfterTest
	public void closeBrowserTest() {
		 //quitDriver(driver);
	}
}
