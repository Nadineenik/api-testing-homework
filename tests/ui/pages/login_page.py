from playwright.sync_api import Page, expect

class LoginPage:
    def __init__(self, page: Page):
        self.page = page
        # Инкапсуляция локаторов (они приватные внутри класса)
        self.username_input = page.locator("#user-name")
        self.password_input = page.locator("#password")
        self.login_button = page.locator("#login-button")
        self.error_message = page.locator("[data-test='error']")

    def open_page(self):
        self.page.goto("https://www.saucedemo.com/", wait_until="domcontentloaded", timeout=60000)
        expect(self.username_input).to_be_visible(timeout=10000)

    def login(self, username, password):
        self.username_input.fill(username)
        self.password_input.fill(password)
        self.login_button.click()

    def check_error_message(self, expected_text):
        expect(self.error_message).to_have_text(expected_text)