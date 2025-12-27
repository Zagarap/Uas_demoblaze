package com.praktikum.rest.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    // --- Locators: Navbar ---
    private final By homeMenu = By.xpath("//a[contains(text(), 'Home')]");
    private final By cartMenu = By.id("cartur");
    private final By logoutMenu = By.id("logout2");

    // --- Locators: Sign Up (Registrasi) ---
    private final By signUpMenu = By.id("signin2");
    private final By signUpUsername = By.id("sign-username");
    private final By signUpPassword = By.id("sign-password");
    private final By signUpButtonModal = By.xpath("//button[text()='Sign up']");

    // --- Locators: Categories ---
    // Phones di CATEGORY LIST (bukan navbar)
    private final By phonesCategory =
            By.xpath("//a[@class='list-group-item' and text()='Phones']");

    private final By laptopsCategory =
            By.xpath("//a[@class='list-group-item' and text()='Laptops']");

    private final By monitorsCategory =
            By.xpath("//a[@class='list-group-item' and text()='Monitors']");

    // --- Constructor ---
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // --- Actions: Categories ---
    public void clickPhonesCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(phonesCategory)).click();
        waitForProductListToReload();
    }

    public void clickLaptopsCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(laptopsCategory)).click();
        waitForProductListToReload();
    }

    public void clickMonitorsCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(monitorsCategory)).click();
        waitForProductListToReload();
    }

    // --- Actions: Navigation ---
    public void clickHome() {
        wait.until(ExpectedConditions.elementToBeClickable(homeMenu)).click();
    }

    public void clickCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartMenu)).click();
    }

    public void logout() {
        // Cek dulu apakah tombol logout ada (artinya sedang login)
        if (!driver.findElements(logoutMenu).isEmpty() && driver.findElement(logoutMenu).isDisplayed()) {
            driver.findElement(logoutMenu).click();
            // Tunggu sampai tombol Sign up muncul kembali (tanda sukses logout)
            wait.until(ExpectedConditions.visibilityOfElementLocated(signUpMenu));
        }
    }

    public String signUp(String username, String password) {
        // 1. Klik menu Sign Up
        wait.until(ExpectedConditions.elementToBeClickable(signUpMenu)).click();

        // 2. Tunggu modal & isi form
        wait.until(ExpectedConditions.visibilityOfElementLocated(signUpUsername)).sendKeys(username);
        driver.findElement(signUpPassword).sendKeys(password);

        // 3. Klik tombol Sign up
        driver.findElement(signUpButtonModal).click();

        // 4. Handle Alert
        String alertText = "";
        try {
            // Tunggu alert muncul
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alertText = alert.getText();
            System.out.println("Sign Up Alert: " + alertText); // Log untuk debug
            alert.accept(); // Tutup alert
        } catch (TimeoutException e) {
            System.out.println("Alert sign up tidak muncul.");
        }

        return alertText;
    }

    // --- Helper Methods ---
    private void waitForProductListToReload() {
        // Tunggu sebentar agar list lama hilang (opsional, untuk stabilitas)
        try { Thread.sleep(500); } catch (InterruptedException e) {}

        // Tunggu JS selesai me-render kartu produk
        wait.until(driver ->
                driver.findElements(By.cssSelector("#tbodyid .card")).size() > 0
        );
    }
}