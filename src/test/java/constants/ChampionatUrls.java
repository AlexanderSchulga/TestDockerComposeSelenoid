package constants;

public enum ChampionatUrls {

    BASE_URL("https://www.championat.com/");

    private String url;

    //Обязательно конструктор
    ChampionatUrls(String url){ //Без модификатора доступа
        this.url = url;

    }
    //Так как private String url;--- метод url приватный
    //Надо уметь получать этот Enum
    public String getBaseUrl(){
        return url;  //метод получения базового url
    }

    ////Метод для получения склеивания ссылок
    //пример одна ссылка главной страницы или самого сайта
    //вторая другая страница
    public String getUrl(){
        return BASE_URL.getBaseUrl() + url;
    }

}
