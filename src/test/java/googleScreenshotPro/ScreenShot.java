package googleScreenshotPro;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ScreenShot {

	WebDriver driver;
	public static String screenshotspath = System.getProperty("user.dir") + "/Screenshots";
	@BeforeTest
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
	}

	@Test
	public void test1() {
		WebElement about = driver.findElement(By.xpath("//a[text()='About']"));
		about.click();
		String title = driver.getTitle();
		System.out.println(title);
		if (title.equals("Google - About Google, Our Culture & Company News")) {
			try {
				takeScreenShot();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.navigate().back();
		WebElement store = driver.findElement(By.xpath("//a[text()='Store']"));
		store.click();
		String title1 = driver.getTitle();
		System.out.println(title1);
		if (title1.equals("Google Store for Google Made Devices & Accessories")) {
			try {
				takeScreenShot();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void takeScreenShot() throws IOException {
		Date date = new Date();
		long name = date.getTime();
		TakesScreenshot screen = (TakesScreenshot) driver;
		File source = screen.getScreenshotAs(OutputType.FILE);
		String filepath = screenshotspath + "/Screen_" + name + ".png";
		File desination = new File(filepath);
		FileUtils.copyFile(source, desination);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
