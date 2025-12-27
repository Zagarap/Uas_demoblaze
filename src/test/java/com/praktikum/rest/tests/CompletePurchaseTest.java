package com.praktikum.rest.tests;

import com.praktikum.rest.base.BaseTest;
import com.praktikum.rest.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CompletePurchaseTest extends BaseTest {

    @Test
    public void testSuccessfulPurchase() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver); // Panggil page baru

        // 1. Setup User & Login
        String uniqueUser = "buyer_" + System.currentTimeMillis();
        homePage.signUp(uniqueUser, "pass123");
        loginPage.login(uniqueUser, "pass123");

        // 2. Beli Laptop (Coba kategori beda)
        homePage.clickLaptopsCategory(); // Pastikan method ini ada di HomePage
        // Kita pakai method yang sama karena strukturnya mirip,
        // tapi idealnya buat method 'addSonyVaioToCart' di ProductPage jika itemnya beda.
        // Untuk contoh ini kita anggap klik produk pertama:
        productPage.addFirstProductToCart();

        // 3. Masuk Cart & Place Order
        homePage.clickCart();
        cartPage.clickPlaceOrderButton();

        // 4. Isi Form Checkout (Pakai CheckoutPage)
        checkoutPage.fillOrderForm("Ihza", "Indonesia", "Jakarta", "1234-5678", "12", "2025");
        checkoutPage.clickPurchaseButton();

        // 5. Verifikasi Sukses
        String actualMsg = checkoutPage.getSuccessMessage();
        Assert.assertEquals(actualMsg, "Thank you for your purchase!");

        checkoutPage.clickOk();
    }
}