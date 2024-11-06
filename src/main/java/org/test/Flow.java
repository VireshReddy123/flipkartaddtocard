package org.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Flow {
	
	@Test
    public void addProductToCart()  {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Navigating to the Amazon Page");
        driver.get("https://www.amazon.in/");

        System.out.println("Entering product name");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Wrist Watches");
        driver.findElement(By.id("nav-search-submit-button")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Leather']/../../.. //i")));
        driver.findElement(By.xpath("//span[text()='Leather']/../../.. //i")).click();
        driver.findElement(By.xpath("//div[@id='brandsRefinements']//li[@aria-label=\"Fastrack\"]//i")).click();

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//a[@aria-label=\"Go to next page, page 2\"]")));
        driver.findElement(By.xpath("//a[@aria-label=\"Go to next page, page 2\"]")).click();

        //Select product
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//div[@class=\"a-section a-spacing-base a-text-center\"])[1]/div[1]")));
        Actions AC = new Actions(driver);
        AC.moveToElement(driver.findElement(By.xpath("(//div[@class=\"a-section a-spacing-base a-text-center\"])[1]/div[1]"))).build().perform();
        driver.findElement(By.xpath("(//div[@class=\"a-section a-spacing-base a-text-center\"])[1]/div[1]")).click();

        //Add To cart in first product
        // Store the main window handle
        String mainWindowHandle = driver.getWindowHandle();

        // Get all window handles
        Set<String> allWindowHandles = driver.getWindowHandles();

        // Switch to the child window
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Add to Cart']")));
        driver.findElement(By.xpath("//span[text()='Add to Cart']/..")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' Skip ']/../span[@id='attachSiNoCoverage-announce']")));
        AC.moveToElement(driver.findElement(By.xpath("//span[text()=' Skip ']/../span[@id='attachSiNoCoverage-announce']"))).click().build().perform();

    }
}
