import java.util.Iterator;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandleWithRegularExp {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://loginpagePractise/");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(".blinkingText")).click();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		String parentWindow = itr.next();
		String childWindow = itr.next();
		driver.switchTo().window(childWindow);
		String ChildWinText=driver.findElement(By.cssSelector(".im-para.red")).getText();
		System.out.println(ChildWinText);
		Pattern pattern = Pattern.compile("[a-zA-Z0-9+._-]+@[a-zA-Z0-9.-]+");
		Matcher matcher = pattern.matcher(ChildWinText);
		//String[] MAILstorage = null;
		String Group = null;
		while(matcher.find()){
			 Group = matcher.group();
            System.out.println("group="+Group);
            
		}
		//MAILstorage=append(MAILstorage,Group);
		driver.switchTo().window(parentWindow);
		driver.findElement(By.id("username")).sendKeys(""+Group+"");

		
	}


}
