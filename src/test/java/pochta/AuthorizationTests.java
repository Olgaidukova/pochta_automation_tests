package pochta;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class AuthorizationTests extends TestBase {

    @Test
    @DisplayName("Проверка авторизации с некорректными логин/пароль")
    void pochtaAuthNegativeTest(){
        step("Открыть сайт Почта России", () -> {
            pochtaPage.openPage();
        });

        step("Перейти на форму авторизации", () -> {
            $(byText("Войти")).click();
        });

        step("Ввести некорректные данные", () -> {
            $("#username").setValue("test@gmail.com");
            $("#userpassword").setValue("12345678");
            $(byText("Войти")).click();
        });

        step("Проверить валидационную ошибку", () -> {
            $(".FormCaption-sc-138okmx-0").shouldHave(text("Неверное имя пользователя или пароль"));
        });
     }
}
