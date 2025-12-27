package com.praktikum.rest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    // --- Locators (Menggunakan By agar konsisten & stabil) ---
    // Tombol hijau "Place Order" di halaman Cart
    private final By placeOrderBtn = By.xpath("//button[@data-toggle='modal' and text()='Place Order']");

    // Tombol "Purchase" di dalam Modal Form
    private final By purchaseBtn = By.xpath("//button[text()='Purchase']");

    // Pesan sukses
    private final By successMsg = By.xpath("//h2[contains(text(), 'Thank you for your purchase!')]");
    private final By okBtn = By.xpath("//button[text()='OK']");

    // --- Constructor ---
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // --- Actions ---

    /**
     * Klik tombol hijau Place Order untuk memunculkan modal form.
     */
    public void clickPlaceOrderButton() {
        // Tunggu tombol muncul & bisa diklik
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
    }

    /**
     * Klik tombol Purchase di dalam modal (tanpa isi data).
     * Digunakan untuk Negative Test Case.
     */
    public void clickPurchaseWithoutFill() {
        // Karena ini dalam modal, pastikan modalnya sudah muncul (visible)
        wait.until(ExpectedConditions.visibilityOfElementLocated(purchaseBtn));
        wait.until(ExpectedConditions.elementToBeClickable(purchaseBtn)).click();
    }

    /**
     * Ambil pesan sukses setelah purchase berhasil.
     */
    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
        return driver.findElement(successMsg).getText();
    }

    /**
     * Klik OK setelah sukses order
     */
    public void clickOk() {
        wait.until(ExpectedConditions.elementToBeClickable(okBtn)).click();
    }

    // Locator tombol Delete (ambil yang pertama ditemukan)
    private final By deleteLink = By.xpath("//a[text()='Delete']");

    public void deleteProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteLink)).click();

        // Tunggu sebentar sampai item hilang dari tabel (tunggu delete link hilang)
        wait.until(ExpectedConditions.invisibilityOfElementLocated(deleteLink));
    }

    // Helper untuk cek apakah cart kosong (opsional)
    public boolean isCartEmpty() {
        // Cek apakah tombol delete sudah tidak ada
        return driver.findElements(deleteLink).isEmpty();
    }
}