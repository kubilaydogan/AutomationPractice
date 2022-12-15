package Pages;

import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ShoppingCart extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(ShoppingCart.class);

    static List<String> itemsInShoppingCart = new ArrayList<>();
    private static final By firstNameField = By.id("first-name");
    private static final By lastNameField = By.id("last-name");
    private static final By zipCodeField = By.id("postal-code");
    private static final By continueButton = By.id("continue");


    public static void verifyItems(DataTable dataTable) {
        List<String> addedItems = dataTable.asList(String.class);
        List<WebElement> list = driver.findElements(By.cssSelector(".inventory_item_name"));
        for (WebElement each : list) {
            itemsInShoppingCart.add(each.getText());
        }
        Assert.assertEquals(addedItems, itemsInShoppingCart);
        list.clear();
    }

    public static void removeItem(String product) {
        itemsInShoppingCart.remove(product.trim());
        ClickOn(driver.findElement(By.xpath("//div[text()='" + product + "']/../..//button[contains(@data-test,'remove')]")));
    }

    public static double getSumOfShoppingCart() {
        double itemPrice, sum = 0;
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        for (WebElement each : list) {
            itemPrice = Double.parseDouble(each.getText().replace("$", ""));
            sum += itemPrice;
        }
        return sum;
    }

    public static void fillCostomerInformation(DataTable dataTable) {
        List<List<String>> info = dataTable.asLists();
        String firstName = info.get(0).get(1);
        String lastName = info.get(1).get(1);
        String zipCode = info.get(2).get(1);

        if (firstName.contentEquals("random")) {
            firstName = faker.name().firstName();
        }
        if (lastName.contentEquals("random")) {
            lastName = faker.name().lastName();
        }
        if (zipCode.contentEquals("random")) {
            zipCode = faker.address().zipCode();
        }

        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(zipCodeField).sendKeys(zipCode);
        driver.findElement(continueButton).click();
    }

    public static void verifyTotalSum() {

        double actualTotal = Double.parseDouble(driver.findElement(By.xpath("//div[@class='summary_total_label']")).getText().replace("Total: $", ""));
        double tax = Double.parseDouble(driver.findElement(By.xpath("//div[@class='summary_tax_label']")).getText().replace("Tax: $", ""));
        double itemTotal = 0;

        List<WebElement> prices = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        for (WebElement each : prices) {
            itemTotal += Double.parseDouble(each.getText().replace("$", ""));
        }

        assertTrue(actualTotal == itemTotal + tax);
    }
}
