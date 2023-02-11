package pochta;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class CalculationTests extends TestBase {

    @Test
    @DisplayName("Проверка формы 'Отправить посылку' на главной странице")
    void pochtaCalculationTest(){
        step("Открыть сайт Почта России", () -> {
            pochtaPage.openPage();
        });

        step("Ввести город отправки", () -> {
            $("#addressFrom").setValue("г Санкт-Петербург");
            $(".DropdownItem-sc-tyrlej-0:nth-child(1) > b:nth-child(3)").click();
        });

        step("Ввести город назначения", () -> {
            $("#addressTo").setValue("г Москва");
            $(By.xpath("//div[@id='main-content']/div/div/div/div[2]/div/div/div/div/div[2]/form/div/div[2]/div/div/div/div[2]/div/div/div[2]/div")).click();
        });

        step("Нажать кнопку Рассчитать", () -> {
            $(".gzYxZp > .Buttonstyles__ButtonTitle-sc-o9c5ps-0").click();
        });

        step("Проверка перехода на Оформление посылки", () -> {
            $(".columns-1").shouldHave(text("Оформить посылку"));
        });
    }

    @CsvSource({
            "Обычный, Обычная доставка",
            "Ускоренный, Ускоренная доставка",
            "EMS, EMS-доставка"
    })
    @ParameterizedTest(name = "Проверка оформления посылки {1}")
    void pochtaDeliveryTest(
            String method,
            String delivery
    ) {
        step("Открыть сайт Почта России", () -> {
            pochtaPage.openPage();
        });

        step("Переход на страницу оформления посылки", () -> {
            pochtaPage.openPage();
            $(byText("Отправить")).click();
            $(".GridBox-sc-172em67-0:nth-child(1) > .Box-sc-7ax6ia-0:nth-child(2) .Font-sc-le1wax-0").click();
        });

        step("Ввести данные для посылки", () -> {
            $("#addressTo").setValue("г Москва");
            $(".jMSeLV .DropdownItem-sc-tyrlej-0:nth-child(1)").click();
            $("#weight").setValue("500 г");
            $(".jMSeLV .DropdownItem-sc-tyrlej-0").click();
        });

        step("Выбрать способ доставки", () -> {
            $(byText(method)).click();
        });

        step("Проверить расчеты доставки", () -> {
            $(byText(delivery)).shouldBe(visible);
        });
    }
}
