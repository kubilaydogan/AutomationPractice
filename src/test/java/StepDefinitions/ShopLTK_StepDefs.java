package StepDefinitions;

import Core.pojos.Customer;
import Pages_LTK.DiscoverPage;
import Pages_LTK.HomePage;
import Pages_LTK.SignUpPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShopLTK_StepDefs {
    HomePage homePage = new HomePage();
    SignUpPage signupPage = new SignUpPage();
    DiscoverPage discoverPage = new DiscoverPage();

    @Given("user is on main page")
    public void userIsOnMainPage() {

        homePage.openHomePage();
        homePage.verifyTitle("LTK | Fashion, Home, Beauty, Fitness and More");
    }

    @When("user signup a new user")
    public void userSignupANewUser(DataTable dataTable) {
        Customer customer = new Customer(dataTable);
        homePage.clickOnSignUpButton();
        signupPage.verifyUserIsOnSignUpPage();
        signupPage.enterEmail(customer.getEmail());
        signupPage.enterPassword(customer.getPassword());
    }

    @Then("^verify user is redirected to ([^\"]*) home page$")
    public void verifyUserIsRedirectedToHomePage(String page)        {
        discoverPage.verifyCurrentPage(page);
    }

    @Then("verify user is notified with {string} message")
    public void verifyUserIsNotifiedWithMessage(String message) {
        signupPage.verifyWarningMessage(message);
    }

    @And("user logs out successfully")
    public void userLogsOut() throws InterruptedException {
        homePage.logout();

    }

}
