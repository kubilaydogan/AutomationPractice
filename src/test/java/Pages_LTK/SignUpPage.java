package Pages_LTK;

import Core.Driver;
import Core.enums.ClickType;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignUpPage extends BasePage {

    public SignUpPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "input[name=email]:last-child")
    public WebElement emailInputField;

    @FindBy(name = "password")
    public WebElement passwordInputField;

    @FindBy(xpath = "//span[text()='sign up to begin shopping']/../..")
    public WebElement signUp;

    private By submitButton = By.xpath("//button[@type='submit']");
    private By pageHeader = By.cssSelector("h1[data-msgid='Welcome to LTK']");


    public void verifyUserIsOnSignUpPage() {
        WebElement pageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1[data-msgid='Sign Up']")));
        assertTrue(pageHeader.getText().contains("Sign Up"));
    }

    public void enterEmail(String email) {
        if(email.equalsIgnoreCase("randomEmail")){
            Faker faker = new Faker();
            email = faker.internet().emailAddress();
        }
        emailInputField.sendKeys(email);
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        continueButton.click();
        WebElement passwordPageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader));
        assertTrue(passwordPageHeader.getText().contains("Welcome to LTK"));
    }

    public void enterPassword(String password) {
        passwordInputField.sendKeys(password);
        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(signUp));
        // signUpButton.click();
        click(signUpButton, ClickType.DEFAULT);
    }

    public void verifyWarningMessage(String message) {
        WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".v-snack__wrapper div p")));
        assertTrue(warningMessage.isDisplayed());
        assertTrue(warningMessage.getText().contains(message));
    }


}
