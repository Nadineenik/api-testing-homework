# Таблица «было → стало»

| Элемент | Было | Стало | Почему |
|---|---|---|---|
| Поле логина | `$("#user-name")` | `$("[data-test='username']")` | стабильный `data-test` вместо id, который может смениться |
| Поле пароля | `$("#password")` | `$("[data-test='password']")` | то же |
| Кнопка логина | `$("#login-button")` | `$("[data-test='login-button']")` | то же |
| Заголовок | `$(".title")` | `$x("//span[@data-test='title']")` | показываем XPath, привязка к `data-test`, а не к классу |
| Бейдж корзины | `$(".shopping_cart_badge")` | `$("[data-test='shopping-cart-badge']")` | класс — вёрстка, `data-test` — контракт |
| Кнопка «в корзину» | `$("#add-to-cart-sauce-labs-backpack")` | `$("[data-test='add-to-cart-sauce-labs-backpack']")` | то же |
| (новое) Ошибка логина | — | `$x("//h3[@data-test='error']")` | нужен для негативного теста, XPath по `data-test` |
| (новое) Контейнер инвентаря | — | `$("[data-test='inventory-container']")` | надёжная проверка загрузки страницы |

