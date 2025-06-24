package page_objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class NavigationPageRPLSelenide {

    private SelenideElement textRpl = $x("/html/body/div[8]/div[3]/h1");


    //Метод проверки, надписи рпл в тексте по середине серым цветом
    public void waitUntilTextRplVisible(){
        textRpl.shouldBe(Condition.visible);


    }

}

