package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import factories.UserFactory;
import models.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.LoginPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Тестирование SauceDemo с паттернами")
public class UiTests {

    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://www.saucedemo.com"; 
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @Tag("smoke")
    @DisplayName("Успешный вход (Chain of invocations + Loadable Component)")
    void successfulLoginTest() {
        // Цепочка вызовов: load() -> isLoaded() -> login() -> isLoaded()
        new LoginPage()
                .load()
                .isLoaded()
                .login(UserFactory.standardUser())
                .isLoaded();
    }

    @Test
    @Tag("regression")
    @DisplayName("Добавление в корзину (Fluent interface)")
    void addToCartTest() {
        new LoginPage()
                .load()
                .login(UserFactory.standardUser())
                .addBackpackToCart()
                .checkCartCount("1");
    }

    // Дополнительный паттерн: Data Provider (через JUnit MethodSource)
    static Stream<User> userProvider() {
        return Stream.of(
                UserFactory.standardUser(),
                new User("problem_user", "secret_sauce")
        );
    }

    @ParameterizedTest(name = "Вход пользователем: {0}")
    @MethodSource("userProvider")
    @DisplayName("Параметризованный вход (Data Provider)")
    void parameterizedLoginTest(User user) {
        new LoginPage()
                .load()
                .login(user)
                .isLoaded();
    }
}
