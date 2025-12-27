package com.praktikum.rest.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    // --- Locators ---
    private final By samsungGalaxyLink = By.linkText("Samsung galaxy s6");
    private final By firstProductLink = By.xpath("//div[@id='tbodyid']//h4/a");
    private final By addToCartBtn = By.xpath("//a[text()='Add to cart']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // --- Actions ---

    public void addSamsungGalaxyToCart() {
        // Ganti klik biasa dengan safeClick agar anti-stale
        safeClick(samsungGalaxyLink);
        addToCartAndHandleAlert();
    }

    public void addFirstProductToCart() {
        // Ganti klik biasa dengan safeClick
        safeClick(firstProductLink);
        addToCartAndHandleAlert();
    }

    // --- HELPER METHODS ---

    /**
     * Method Sakti: Mencoba klik elemen. Jika error Stale (elemen berubah),
     * dia akan mencoba lagi sampai 3 kali.
     */
    private void safeClick(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                // Tunggu elemen clickable
                wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
                break; // Jika berhasil, keluar dari loop
            } catch (StaleElementReferenceException e) {
                // Jika stale, diamkan, loop akan mengulang dan mencari elemen baru
                System.out.println("Element stale, mencoba klik ulang... (Attempt " + (attempts + 1) + ")");
            }
            attempts++;
        }
    }

    private void addToCartAndHandleAlert() {
        // 1. Tunggu tombol Add to Cart muncul & Klik
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBtn));
        driver.findElement(addToCartBtn).click();

        // 2. Handle Alert "Product added"
        try {
            WebDriverWait waitAlert = new WebDriverWait(driver, Duration.ofSeconds(5));
            waitAlert.until(ExpectedConditions.alertIsPresent());

            Alert alert = driver.switchTo().alert();
            // System.out.println("Product Page Alert: " + alert.getText());
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("Alert product added tidak muncul (Timeout).");
        }
    }
}