package pochta;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase{

    @ValueSource(strings = {
            "42006175157583",
            "80520775364115"
    })
    @ParameterizedTest(name = "Отслеживается трек-номер {0}")
    void pochtaSearchTest(String trackNumber) {
        step("Открыть сайт Почта России", () -> {
            pochtaPage.openPage();
        });

        step("Отследить отправления", () -> {
            $("#barcode").setValue(trackNumber).pressEnter();
        });

        step("Проверить результаты поиска", () -> {
            $("#tracking-card-portal").shouldHave(text(trackNumber));
        });
    }
}
