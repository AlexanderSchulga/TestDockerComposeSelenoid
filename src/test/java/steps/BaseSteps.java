package steps;

import com.codeborne.selenide.Selenide;
import constants.ChampionatUrls;
import io.cucumber.java.en.Given;

public class BaseSteps {

    //пример хранения и передачи параметров в feature вместо стринг. инта и т.д.
    //public static Map<String, Object> container = new HashMap<>();
    //Но, обычно это делается через DI
    @Given("Follow the link {string}")
    public void openPage(String url) {
        Selenide.open(ChampionatUrls.BASE_URL.getBaseUrl());
        //Selenide.open(url); // переданный URL

    }

}

