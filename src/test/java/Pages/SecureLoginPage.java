package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SecureLoginPage {

    WebDriver driver;

    WebElement logOutButton, successfulMessage;

    public SecureLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getLogOutButton() {
        return driver.findElement(By.cssSelector(".button.secondary.radius"));
    }

    public WebElement getSuccessfulMessage() {
        return driver.findElement(By.id("flash"));
    }
}
