package pages;

import static com.codeborne.selenide.Selenide.open;

public abstract class BasePage<T> {
    public abstract T load();
    public abstract T isLoaded();
}