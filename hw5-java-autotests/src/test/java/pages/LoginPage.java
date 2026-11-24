package pages;

import com.codeborne.selenide.SelenideElement;
import elements.CustomInput;
import models.User;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage<LoginPage> {

    // CSS по data-test: семантический атрибут, не завязан на вёрстку
    private final CustomInput usernameInput = new CustomInput($("[data-test='username']"));
    private final CustomInput passwordInput = new CustomInput($("[data-test='password']"));
    private final SelenideElement loginBtn = $("[data-test='login-button']");

    // XPath: показываем второй способ, тоже по data-test
    private final SelenideElement errorMessage = $x("//h3[@data-test='error']");

    @Override
    public LoginPage load() {
        open("/");
        return this;
    }

    @Override
    public LoginPage isLoaded() {
        usernameInput.getElement().shouldBe(visible);
        return this;
    }

    public InventoryPage login(User user) {
        usernameInput.type(user.username());
        passwordInput.type(user.password());
        loginBtn.click();
        return new InventoryPage();
    }

    public LoginPage checkError(String text) {
        errorMessage.shouldBe(visible).shouldHave(text(text));
        return this;
    }

    public LoginPage loginExpectingError(User user) {
    usernameInput.type(user.username());
    passwordInput.type(user.password());
    loginBtn.click();
    return this;
    }
}
