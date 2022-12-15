package Pages_LTK;

import Core.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage {

    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[@href='/signup']")
    public WebElement signUpButton;


    public void openHomePage() {
       Driver.getDriver().get(SHOPLTK_URL);
    }

    public void clickOnSignUpButton() {
        signUpButton.click();
    }




}
