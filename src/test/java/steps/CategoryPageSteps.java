package steps;

import io.cucumber.java.en.Then;
import page_objects.NavigationPageRPLSelenide;

public class CategoryPageSteps {

    private NavigationPageRPLSelenide navigationPageRPLSelenide = new NavigationPageRPLSelenide();

    @Then("Search and comparison")
    public void waitUntilTextRplVisible() {

        navigationPageRPLSelenide.waitUntilTextRplVisible();
    }

}
