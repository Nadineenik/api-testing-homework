import pytest
from playwright.sync_api import Page
from tests.ui.pages.login_page import LoginPage
from tests.ui.pages.inventory_page import InventoryPage

# 1. Тест: Успешная авторизация (Positive)
def test_successful_login(page: Page):
    login_page = LoginPage(page)
    login_page.open_page()
    login_page.login("standard_user", "secret_sauce")
    
    inventory_page = InventoryPage(page)
    inventory_page.check_title() # Проверяем, что перешли на страницу товаров

# 2. Тест: Авторизация заблокированного пользователя (Negative)
def test_locked_out_user(page: Page):
    login_page = LoginPage(page)
    login_page.open_page()
    login_page.login("locked_out_user", "secret_sauce")
    
    # Проверяем текст ошибки, который ожидает Playwright
    login_page.check_error_message("Epic sadface: Sorry, this user has been locked out.")

# 3. Тест: Добавление товара в корзину
def test_add_item_to_cart(page: Page):
    login_page = LoginPage(page)
    login_page.open_page()
    login_page.login("standard_user", "secret_sauce")
    
    inventory_page = InventoryPage(page)
    inventory_page.add_backpack_to_cart()
    inventory_page.check_cart_badge_count(1) # Проверяем, что на иконке корзины появилась "1"

# 4. Тест: Проверка пустой формы авторизации
def test_login_empty_fields(page: Page):
    login_page = LoginPage(page)
    login_page.open_page()
    login_page.login("", "") # Вводим пустые строки
    
    login_page.check_error_message("Epic sadface: Username is required")