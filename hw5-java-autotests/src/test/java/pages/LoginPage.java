package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement usernameInput = $("#user-name");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginBtn = $("#login-button");
    private final SelenideElement errorMsg = $("[data-test='error']");

    public void login(String user, String pass) {
        usernameInput.setValue(user);
        passwordInput.setValue(pass);
        loginBtn.click();
    }

    public SelenideElement getErrorMsg() {
        return errorMsg;
    }
}
