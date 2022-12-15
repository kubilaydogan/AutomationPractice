package Core.Utilities;

import Core.Driver;
import Core.enums.ClickType;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonMethods {
    public static WebDriver driver = Driver.getDriver();
    public static Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(25L));
    public static JavascriptExecutor js = (JavascriptExecutor) driver;
    public static Actions actions = new Actions(driver);
    public static Faker faker = new Faker();

    public static String removeNonNumericCharacters(String itemNumber) {
        // example: 3rd  --> 3
        return itemNumber.replaceAll("[^\\d.]", "");
    }

    public static void clickElement(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
    }

    public static void clickElement(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public static void click(WebElement element, ClickType clickType){
        switch(clickType){
            case DEFAULT:
                element.click();
                break;
            case ACTIONS:
                actions.moveToElement(element).click().perform();
                break;
            case JSEXECUTOR:
                js.executeScript("arguments[0].scrollIntoView();", element);
                js.executeScript("arguments[0].click();", element);
        }
    }

    public void verifyTitle(String expectedTitle) {
        Assert.assertTrue(Driver.getDriver().getTitle().contains(expectedTitle));
    }

    /**
     * Waits and gets the text of an WebElement.
     *
     * @param webElement WebElement to wait and get the text.
     * @return Text of element.
     */
    public static String getTextElement(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getText();
    }


}
