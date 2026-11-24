package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class InventoryPage extends BasePage<InventoryPage> {

    // Контейнер — надёжный маркер загрузки страницы
    private final SelenideElement inventoryContainer = $("[data-test='inventory-container']");

    // XPath по data-test
    private final SelenideElement title = $x("//span[@data-test='title']");

    // CSS по data-test
    private final SelenideElement cartBadge = $("[data-test='shopping-cart-badge']");

    @Override
    public InventoryPage load() {
        return this;
    }

    @Override
    public InventoryPage isLoaded() {
        inventoryContainer.shouldBe(visible);
        title.shouldBe(visible).shouldHave(text("Products"));
        return this;
    }

    public InventoryPage addToCartByProductName(String productName) {
        $x("//div[@data-test='inventory-item']" +
                "[.//div[@data-test='inventory-item-name' and text()='" + productName + "']]" +
                "//button[starts-with(@data-test,'add-to-cart')]")
                .click();
        return this;
    }

    // Сахар для частого кейса — использует тот же универсальный метод
    public InventoryPage addBackpackToCart() {
        return addToCartByProductName("Sauce Labs Backpack");
    }

    public InventoryPage checkCartCount(String count) {
        cartBadge.shouldHave(text(count));
        return this;
    }
}