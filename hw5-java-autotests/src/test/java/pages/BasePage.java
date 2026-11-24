package pages;


public abstract class BasePage<T> {
    public abstract T load();
    public abstract T isLoaded();
}