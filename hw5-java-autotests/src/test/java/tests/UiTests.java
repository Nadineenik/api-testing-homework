package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import pages.LoginPage;
import pages.InventoryPage;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Тестирование SauceDemo")
public class UiTests {

    // Базовые аннотации: @BeforeEach
    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
        open("https://www.saucedemo.com/");
    }

    // Базовые аннотации: @AfterEach
    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    // Дополнительная: @Nested
    @Nested
    @DisplayName("Тесты авторизации")
    class LoginTests {

        // Базовые: @Test, @DisplayName, @Tag, assertAll
        @Test
        @Tag("smoke")
        @DisplayName("Успешный вход в систему")
        void successfulLoginTest() {
            LoginPage loginPage = new LoginPage();
            loginPage.login("standard_user", "secret_sauce");
            
            InventoryPage inventoryPage = new InventoryPage();
            
            // assertAll позволяет проверить несколько условий разом
            assertAll(
                () -> inventoryPage.title.shouldHave(text("Products")),
                () -> inventoryPage.itemNames.shouldHave(com.codeborne.selenide.CollectionCondition.sizeGreaterThan(0))
            );
        }

        // Дополнительная: @ParameterizedTest
        @ParameterizedTest(name = "Вход с пользователем: {0}")
        @CsvSource({
            "standard_user, secret_sauce, Products",
            "problem_user, secret_sauce, Products"
        })
        @DisplayName("Параметризованный вход")
        void parameterizedLoginTest(String user, String pass, String expectedTitle) {
            LoginPage loginPage = new LoginPage();
            loginPage.login(user, pass);
            new InventoryPage().title.shouldHave(text(expectedTitle));
        }

        // Базовые: @Disabled (тест не будет запускаться)
        @Test
        @Disabled("Тест временно отключен, баг BUG-01")
        @DisplayName("Вход заблокированного пользователя")
        void lockedOutUserTest() {
            LoginPage loginPage = new LoginPage();
            loginPage.login("locked_out_user", "secret_sauce");
            loginPage.getErrorMsg().shouldBe(visible);
        }
    }

     // Дополнительная: @TestFactory (Динамические тесты)
    @TestFactory
    @DisplayName("Динамическая проверка цен на товары")
    Stream<DynamicTest> checkAllItemPrices() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");
        
        InventoryPage inventoryPage = new InventoryPage();
        
        // Берем список текстов (цен) и превращаем его в Stream
        return inventoryPage.itemPrices.texts().stream()
            .map(priceText -> DynamicTest.dynamicTest(
                "Проверка цены: " + priceText,
                () -> {
                    // Проверяем, что текст начинается со знака доллара
                    if (!priceText.startsWith("$")) {
                        throw new AssertionError("Цена должна начинаться с $, но была: " + priceText);
                    }
                }
            ));
    }

    // Дополнительная: Таймаут (тест упадет, если выполнится дольше 5 секунд)
    @Test
    @Timeout(5)
    @Tag("regression")
    @DisplayName("Добавление товара в корзину")
    void addToCartTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");
        
        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.addBackpackToCart();
        
        inventoryPage.cartBadge.shouldHave(text("1"));
    }
}
