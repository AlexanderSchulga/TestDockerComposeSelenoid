package page_objects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class NavigationBarPageSelenide {

    private static final int RPL_COUNT_COMMAND = 16;

    //Локатор ишущий не от корня, а от родительского элемента, так как точку поставили
    //Этот локатор ищет название последней команды
    private static final String NAME_CLUB = ".//*[@class='table-item__name']";
    private ElementsCollection command = $$(".tournament-table tbody tr");



    //private SelenideElement footballClick = $x("/html/body/header/div[1]/div/nav/ul/li[1]");
    private SelenideElement footballClick = $(".header-menu-item._football");



    //private SelenideElement rplClick = $x("/html/body/header/div[1]/div/nav/ul/li[1]/noindex/div/div/a[1]");
    private SelenideElement rplClick = $("a[data-label='football-russiapl']");



    private SelenideElement nameClub = $x("/html/body/div[8]/div[5]/div[1]/div[1]/div[1]/div/table/tbody/tr[10]/td[2]/a");

    //название команды на другой стронице
    private SelenideElement elevened = $x("/html/body/div[9]/div[4]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/a");
    public void clickFootball() {
        //footballClick.shouldBe(Condition.visible);
        //footballClick.click();
        footballClick.shouldBe(visible).click();
    }
    public void clickRpl() {

        //rplClick.shouldBe(visible);
        //rplClick.click(); //Кликаем на него
        footballClick.shouldBe(visible).hover();
        rplClick.shouldBe(visible).click();

    }
    //метод получает все строки и скролится до последней
    public void getCommandAndScrollToThePoint(){

        //проверка коллекции на 16 команд
        command.shouldHave(CollectionCondition.size(RPL_COUNT_COMMAND));


        command.last().scrollIntoView(false);//скролл до нижней или верхней
        //границы элемента последнего
        command.last().shouldBe(visible);


        String lastNameClub = command.get(9).$x(NAME_CLUB).getText();

        nameClub.click();

        elevened.scrollIntoView(false);//скрол до названия


        SelenideElement lastNameClubClick = $x("/html/body/div[9]/div[4]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]");
        lastNameClubClick.shouldBe(Condition.exactText(lastNameClub));

        System.out.println(lastNameClub);
    }

}

