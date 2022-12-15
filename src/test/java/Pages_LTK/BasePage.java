package Pages_LTK;

import Core.Driver;
import Core.Utilities.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class BasePage extends CommonMethods {
    public static final String SHOPLTK_URL = "https://www.shopltk.com/";
    private final By forYouTab = By.cssSelector("a:first-of-type .v-chip__content");

    public void verifyCurrentPage(String page) {
        switch (page.toLowerCase()){
            case "discover":
                assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(forYouTab)).getText().contains("For You"));
                assertTrue(Driver.getDriver().getCurrentUrl().contains("https://www.shopltk.com/home/for-you"));
                assertTrue(Driver.getDriver().getTitle().contains("Discover content from our community of Creators | LTK"));
                break;
            // ...
        }
    }

    public void logout() throws InterruptedException {
        WebElement profileIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".capsule-consumer-profile-outline-24")));
        profileIcon.click();
        WebElement signOutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Sign out']")));
        signOutButton.click();
        Thread.sleep(2000);
        HomePage homePage = new HomePage();
        assertTrue(homePage.signUpButton.isDisplayed());
    }
}
