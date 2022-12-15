package Pages;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class ProductsPage extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(ProductsPage.class);
    private static final WebElement sortProductsDropdown = driver.findElement(By.cssSelector(".product_sort_container"));
    public static By shoppingCart = By.xpath("//a[@class='shopping_cart_link']");
    private static List<WebElement> productsList;

    public static void sortProducts(String sortType) {
        // supported parameters: Price (high to low)|Price (low to high)|Name (A to Z)|Name (Z to A)

        // Select select = new Select(sortProductsDropdown);
        Select select = new Select(driver.findElement(By.cssSelector(".product_sort_container")));
        select.selectByVisibleText(sortType);
        log.info("Products are sorted by: {}", sortType);
        productsList = driver.findElements(By.cssSelector(".inventory_item"));
    }

    public static void addSingleItemToCart(String product) {
        driver.findElement(By.xpath("//div[text()='" + product.trim() + "']/ancestor::div[@class='inventory_item_description']//button")).click();
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void addItemToCart(String product) {
        driver.findElement(By.xpath("//div[text()='" + product.trim() + "']/ancestor::div[@class='inventory_item_description']//button")).click();
    }

    public  void addItemsToShoppingCart(List<String> items) {
        items.forEach(this::addItemToCart);
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void addMultipleItemsToShoppingCart(DataTable items) {
        List<Map<String, String>> list = items.asMaps(String.class, String.class);

        for (Map each : list) {
            addSingleItemToCart(each.get("item").toString());
        }
    }


    public static void goToShoppingCart() {
        ClickOn(driver.findElement(shoppingCart));
    }

    public static void add_nthItemToCart(String itemNumber) {
        itemNumber = removeNonNumericCharacters(itemNumber);
        String item = "(//div[@class='inventory_item_description']//button)[" + itemNumber + "]";
        driver.findElement(By.xpath(item)).click();
    }

    public static void remove_nthItemToCart(String itemNumber) {
        add_nthItemToCart(itemNumber);  // same methods works for removing also
    }

    public static void verifyCountOfItems(int expectedCount) {
        String n = driver.findElement(By.cssSelector(".shopping_cart_badge")).getText();
        int actualItemCount = Integer.parseInt(n);
        assertEquals(expectedCount, actualItemCount);
    }

    public static void verifySelectedDrowdownOption(String expected) {
        String actual = driver.findElement(By.xpath("//span[@class='active_option']")).getText();
        assertEquals(actual, expected.toUpperCase());
    }

    public static String getSelectedDrowdownOption(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getText();
    }

    public boolean isLowestPriceItemDisplayedFirst() {
        float firstItemPrice = getPriceFromItem(productsList.get(0));
        float minimumPrice = productsList.stream().map(this::getPriceFromItem)
                .min(Comparator.naturalOrder()).get();
        return firstItemPrice == minimumPrice;
    }

    private float getPriceFromItem(WebElement item) {
        String priceText = item.findElement(By.cssSelector(".inventory_item_price")).getText().replace("$", "");
        return Float.parseFloat(priceText);
    }

}
