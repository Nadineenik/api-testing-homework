package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class InventoryPage extends BasePage<InventoryPage> {
    private final SelenideElement title = $(".title");
    private final SelenideElement cartBadge = $(".shopping_cart_badge");
    private final SelenideElement addBackpackBtn = $("#add-to-cart-sauce-labs-backpack");

    @Override
    public InventoryPage load() {
        return this;
    }

    @Override
    public InventoryPage isLoaded() {
        title.shouldBe(visible).shouldHave(text("Products"));
        return this;
    }

    public InventoryPage addBackpackToCart() {
        addBackpackBtn.click();
        return this;
    }

    public InventoryPage checkCartCount(String count) {
        cartBadge.shouldHave(text(count));
        return this;
    }
}
