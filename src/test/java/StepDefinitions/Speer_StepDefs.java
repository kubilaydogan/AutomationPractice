package StepDefinitions;

import Pages_Wiki.WikiPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.io.IOException;
import static Core.Utilities.JSONUtils.*;


public class Speer_StepDefs {

    @Given("user is on a {string}")
    public void user_is_on_a(String link) {
        WikiPage.openPage(link);
    }

    @When("all embedded wikipedia links in a page are stored in a list")
    public void all_embedded_wikipedia_links_in_a_page_are_stored_in_a_list() {
        WikiPage.getAllLinks();
    }

    @When("repeat previous step for {int} times for newly found links")
    public void repeat_previous_step_for_times(int n) {
        WikiPage.getAllLinksInTheList(n);
    }

    @When("write the links in a json file")
    public void write_the_links_in_a_json_file() throws IOException {
        writeToJsonFile("links.json");

    }


}
