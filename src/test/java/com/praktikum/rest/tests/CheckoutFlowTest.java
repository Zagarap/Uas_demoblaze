package com.praktikum.rest.tests;

import com.praktikum.rest.base.BaseTest;
import com.praktikum.rest.pages.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutFlowTest extends BaseTest {

    @Test
    public void testCheckoutFlow() {

        // 1. Inisialisasi Page Objects
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        // 2. Buat Akun Dinamis (Supaya tidak error "User already exists" atau "Wrong password")
        // Menggunakan timestamp agar username selalu unik (contoh: ihza_17098234)
        String uniqueUser = "ihza_" + System.currentTimeMillis();
        String password = "password123";

        System.out.println("--- START TEST ---");
        System.out.println("Registering new user: " + uniqueUser);

        // 3. Sign Up & Login
        homePage.signUp(uniqueUser, password);
        loginPage.login(uniqueUser, password);

        // 4. Pilih Produk
        // Alert "Product added" sudah ditangani secara otomatis di dalam method addSamsungGalaxyToCart()
        // (Pastikan kamu sudah update ProductPage.java sesuai kode sebelumnya)
        homePage.clickPhonesCategory();
        productPage.addSamsungGalaxyToCart();

        // 5. Masuk ke Cart
        homePage.clickCart();

        // 6. Proses Checkout
        // Klik tombol hijau "Place Order"
        cartPage.clickPlaceOrderButton();

        // 7. Negative Test: Klik Purchase tanpa mengisi form
        cartPage.clickPurchaseWithoutFill();

        // 8. Verifikasi (Assert) Alert Error Muncul
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String alertMsg = alert.getText();
        System.out.println("Alert message received: " + alertMsg);

        // Cek apakah pesan alert sesuai ekspektasi
        Assert.assertEquals(alertMsg, "Please fill out Name and Creditcard.");

        // Tutup alert
        alert.accept();

        System.out.println("--- TEST PASSED ---");
    }
}