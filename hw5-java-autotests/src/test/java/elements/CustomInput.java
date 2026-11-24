package elements;

import com.codeborne.selenide.SelenideElement;

public class CustomInput {
    private final SelenideElement element;

    public CustomInput(SelenideElement element) {
        this.element = element;
    }

    public CustomInput type(String text) {
        element.clear();
        element.setValue(text);
        return this;
    }

    public SelenideElement getElement() {
        return element;
    }
}
