package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InventoryPage {
    public final SelenideElement title = $(".title");
    public final SelenideElement cartBadge = $(".shopping_cart_badge");
    public final SelenideElement addBackpackBtn = $("#add-to-cart-sauce-labs-backpack");
    public final ElementsCollection itemNames = $$(".inventory_item_name");
    public final ElementsCollection itemPrices = $$(".inventory_item_price");

    public void addBackpackToCart() {
        addBackpackBtn.click();
    }
}
