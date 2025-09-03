package page_objects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class NavigationPageRPLSelenide {
    //Локатор ишущий не от корня, а от родительского элемента, так как точку поставил
    private static final String NAME_CLUB = ".//*[@class='table-item__name']";
    private SelenideElement textRpl = $x("/html/body/div[8]/div[3]/h1");
    private ElementsCollection command = $$(".tournament-table tbody tr");
    private static final int RPL_COUNT_COMMAND = 16;
    private SelenideElement nameClub = $x("/html/body/div[8]/div[5]/div[1]/div[1]/div[1]/div/table/tbody/tr[10]/td[2]/a");



    //Метод проверки, надписи рпл в тексте по середине серым цветом
    public void waitUntilTextRplVisible(){
        textRpl.shouldBe(Condition.visible);
    }

    public void getCommandAndScrollToThePoint(){

        //проверка коллекции на 16 команд
        command.shouldHave(CollectionCondition.size(RPL_COUNT_COMMAND));

        command.last().scrollIntoView(false);//скролл до нижней или верхней
        //границы элемента последнего
        command.last().shouldBe(Condition.visible);

        String lastNameClub = command.get(9).$x(NAME_CLUB).getText();
        nameClub.click();

        System.out.println(lastNameClub);
    }

}

