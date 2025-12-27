package com.praktikum.rest.tests;

import com.praktikum.rest.base.BaseTest;
import com.praktikum.rest.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteFromCart extends BaseTest {

    @Test
    public void testDeleteFromCart() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        // 1. Login
        String uniqueUser = "deleter_" + System.currentTimeMillis();
        homePage.signUp(uniqueUser, "123");
        loginPage.login(uniqueUser, "123");

        // 2. Tambah Barang
        homePage.clickPhonesCategory();
        productPage.addSamsungGalaxyToCart();

        // 3. Masuk Cart
        homePage.clickCart();

        // 4. Hapus Barang
        cartPage.deleteProduct();

        // 5. Assert Cart Kosong
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart seharusnya kosong setelah delete!");
    }
}