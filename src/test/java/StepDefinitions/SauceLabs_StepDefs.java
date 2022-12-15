package StepDefinitions;

import Pages.BasePage;
import Pages.LoginPage;
import Pages.ProductsPage;
import Pages.ShoppingCart;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SauceLabs_StepDefs {
    static WebDriver driver = BasePage.driver;

    @Given("user is on landing page")
    public void user_is_on_landing_page() {
        LoginPage.openLandingPage();
    }

    @When("user log into the site and resets app state")
    public void user_log_into_the_site2(DataTable credentials) {
        LoginPage.login(credentials);
        LoginPage.resetAppState();
    }

    @When("user log into the site")
    public void user_log_into_the_site(DataTable credentials) {
        LoginPage.login(credentials);
    }

    @And("user sort the items by {string}")
    public void userSortTheItemsBy(String option) {
        ProductsPage.sortProducts(option);
        ProductsPage.verifySelectedDrowdownOption(option);
    }

//    @And("user add {string} to shopping cart")
//    public void userAdd(String product) {
//        ProductsPage.addSingleItemToCart(product);
//    }
//
//    @When("user removes {string} from shopping cart")
//    public void userRemovesFromShoppingCart(String product) {
//        ShoppingCart.removeItem(product);
//    }

    @And("^user (add|removes) \"([^\"]*)\" (?:to|from) shopping cart$")
    public void shoppingCart(String transaction, String product) {

        switch (transaction) {
            case "add":
                ProductsPage.addSingleItemToCart(product);
                break;
            case "removes":
                ShoppingCart.removeItem(product);
                break;
        }
    }

    @And("user add following items to shopping cart")
    public void userAddFollowingItems(DataTable items) {
        ProductsPage.addMultipleItemsToShoppingCart(items);
    }

    @And("user add following items to cart")
    public void userAddFollowingItemsToCart(List<String> items) {
        new ProductsPage().addItemsToShoppingCart(items);
    }

    @And("user navigates to the shopping cart")
    public void userNavigatesToTheShoppingCart() {
        ProductsPage.goToShoppingCart();
    }

    @Then("verify that the items that you added are in the cart")
    public void verifyThatTheItemsThatYouAddedAreInTheCart() {

    }

    @Then("verify that the added items are present in the cart")
    public void verifyThatTheAddedItemsArePresentInTheCart(DataTable dataTable) {
        ShoppingCart.verifyItems(dataTable);
    }

    @And("clicks on {string}")
    public void clicksOn(String button) {
        switch (button) {
            case "Checkout":
                ShoppingCart.ClickOn(driver.findElement(By.id("checkout")));
                break;
            case "Continue Shopping":
                ShoppingCart.ClickOn(driver.findElement(By.id("continue-shopping")));
                break;
        }
    }

    @And("user enters checkout information")
    public void userEntersCheckoutInformation(DataTable information) {
        ShoppingCart.fillCostomerInformation(information);
    }

    @Then("verify total sum in checkout overview")
    public void verifyTotalSum() {
        ShoppingCart.verifyTotalSum();
    }

    @When("^user adds (.+) item to shopping cart$")
    public void user_adds_item_to_shopping_cart(String itemNumber) {
        ProductsPage.add_nthItemToCart(itemNumber);
    }

    @When("^user removes (.+) item from shopping cart$")
    public void user_removes_item_from_shopping_cart(String itemNumber) {
        ProductsPage.add_nthItemToCart(itemNumber);
    }

    @And("wait for {int} seconds \\(for demo)")
    public void waitForSeconds(int second) throws InterruptedException {
        Thread.sleep(second * 1000L);
    }

    @Then("verify shopping cart contains {int} item")
    public void verifyShoppingCartContainsItem(int expectedCount) {
        ProductsPage.verifyCountOfItems(expectedCount);
    }

    @When("cache is cleared")
    public void cacheIsCleared() {
        LoginPage.clearCache();
    }

    @Given("the user logins with valid credentials")
    public void theUserLoginsWithValidCredentials() {
        LoginPage.openLandingPage();
        LoginPage.loginWithValidCredentials();
        LoginPage.resetAppState();
    }

//    @When("^the user selects \"([^\"]*)\" as sort option$")
//    public void theUserSelectsOptionToSortProducts(String option) {
//        ProductsPage.sortProducts(option);
//        ProductsPage.verifySelectedDrowdownOption(option);
//    }

    @Then("verify that the item with lowest price is displayed first")
    public void verifyThatTheItemWithLowestPriceIsDisplayedFirst() {
        assertTrue(new ProductsPage().isLowestPriceItemDisplayedFirst(), "The item with lowest price is not displayed first.");
    }
}
