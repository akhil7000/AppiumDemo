import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoSel {

    public static void main(String args[]){
        System.setProperty("selenide.browser", "Chrome");
        open("http://google.com");
        $(By.name("q")).setValue("Selenide").pressEnter();

    }
}
