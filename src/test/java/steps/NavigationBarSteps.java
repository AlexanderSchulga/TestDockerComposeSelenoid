package steps;

import io.cucumber.java.en.When;
import page_objects.NavigationBarPageSelenide;

public class NavigationBarSteps {

    private NavigationBarPageSelenide navigationBarPageSelenide = new NavigationBarPageSelenide();

    @When("Menu and click")
    public void goToSpecificGenre() {

        navigationBarPageSelenide.clickFootball();
        navigationBarPageSelenide.clickRpl();

    }

}
