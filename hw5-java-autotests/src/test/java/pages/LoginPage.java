package pages;

import com.codeborne.selenide.SelenideElement;
import elements.CustomInput;
import models.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage<LoginPage> {
    private final CustomInput usernameInput = new CustomInput($("#user-name"));
    private final CustomInput passwordInput = new CustomInput($("#password"));
    private final SelenideElement loginBtn = $("#login-button");

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
}
