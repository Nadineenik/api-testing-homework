from playwright.sync_api import Page, expect

class InventoryPage:
    def __init__(self, page: Page):
        self.page = page
        self.cart_badge = page.locator(".shopping_cart_badge")
        self.add_to_cart_backpack = page.locator("#add-to-cart-sauce-labs-backpack")
        self.title = page.locator(".title")

    def check_title(self):
        expect(self.title).to_have_text("Products")

    def add_backpack_to_cart(self):
        self.add_to_cart_backpack.click()

    def check_cart_badge_count(self, count):
        expect(self.cart_badge).to_have_text(str(count))