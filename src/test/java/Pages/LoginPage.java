package Pages;

import Core.Utilities.ConfigurationReader;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertTrue;


public class LoginPage extends BasePage{
    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    private static final By usernameField = By.id("user-name");
    private static final By passwordField = By.id("password");
    private static final By loginButton = By.id("login-button");
    private static final By menuButton = By.cssSelector(".bm-burger-button");
    private static final By closeMenuButton = By.cssSelector("#react-burger-cross-btn");
    private static final By resetAppStateButton = By.id("reset_sidebar_link");

    public static void openLandingPage() {
        driver.get(landingPageURL);
    }

    public static void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();

        try{
            assertTrue(driver.findElement(By.xpath("//span[@class='title']")).getText().contentEquals("PRODUCTS"));
            log.info("{} has logged in.", username);
        }catch(NoSuchElementException e){
            log.error("Login failed for {} !!", password);
        }
    }

    public static void login(DataTable dataTable) {
        List<List<String>> credentials = dataTable.asLists();
        String usernameValue = credentials.get(0).get(1);
        String passwordValue = credentials.get(1).get(1);
        login(usernameValue, passwordValue);
    }

    public static void loginWithValidCredentials() {
        login(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
    }

    public static void clearCache() {
        driver.manage().deleteAllCookies();
        driver.get("chrome://settings/clearBrowserData");

        WebElement shadowHost = driver.findElement(By.cssSelector("settings-ui"));
        SearchContext shadowRoot1 = (SearchContext) js.executeScript("return arguments[0].shadowRoot", shadowHost);
        WebElement shadowContent1 = shadowRoot1.findElement(By.cssSelector("settings-main"));

        SearchContext shadowRoot2 = (SearchContext) js.executeScript("return arguments[0].shadowRoot", shadowContent1);
        WebElement shadowContent2 = shadowRoot2.findElement(By.cssSelector("settings-basic-page"));

        SearchContext shadowRoot3 = (SearchContext) js.executeScript("return arguments[0].shadowRoot", shadowContent2);
        WebElement shadowContent3 = shadowRoot3.findElement(By.cssSelector("settings-section > settings-privacy-page"));

        SearchContext shadowRoot4 = (SearchContext) js.executeScript("return arguments[0].shadowRoot", shadowContent3);
        WebElement shadowContent4 = shadowRoot4.findElement(By.cssSelector("settings-clear-browsing-data-dialog"));

        SearchContext shadowRoot5 = (SearchContext) js.executeScript("return arguments[0].shadowRoot", shadowContent4);
        WebElement clearDataButton = shadowRoot5.findElement(By.cssSelector("#clearBrowsingDataConfirm"));

        wait.until(ExpectedConditions.elementToBeClickable(clearDataButton));
        clearDataButton.click();
    }

    public static void resetAppState() {
        clickElement(menuButton);
        clickElement(resetAppStateButton);
        clickElement(closeMenuButton);
    }
}
