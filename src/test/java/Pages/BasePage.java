package Pages;


import Core.Utilities.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class BasePage extends CommonMethods {
    static String landingPageURL = "https://www.saucedemo.com/";

    public static void ClickOn(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        highlight(element);
        element.click();
    }

    public static void SendKeys(WebElement element, String data ){
        wait.until(ExpectedConditions.visibilityOf(element));
        highlight(element);
        element.clear();
        element.sendKeys(data);
    }

    public static void highlight(WebElement element){
        //Yellow background color with solid red color border.
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }
}
