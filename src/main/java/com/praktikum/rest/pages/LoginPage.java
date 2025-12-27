package com.praktikum.rest.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    private final By loginMenu = By.id("login2");
    private final By usernameInput = By.id("loginusername");
    private final By passwordInput = By.id("loginpassword");
    private final By loginButton = By.xpath("//button[text()='Log in']");
    private final By loggedUser = By.id("nameofuser");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void login(String user, String pass) {
        // 1. Buka modal & Tunggu field (Code lama kamu)
        wait.until(ExpectedConditions.elementToBeClickable(loginMenu)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));

        driver.findElement(usernameInput).clear();
        driver.findElement(passwordInput).clear();
        driver.findElement(usernameInput).sendKeys(user);
        driver.findElement(passwordInput).sendKeys(pass);

        // 2. Klik tombol login
        driver.findElement(loginButton).click();

        // ---------------------------------------------------------
        // PERBAIKAN LOGIKA HANDLING ALERT
        // ---------------------------------------------------------
        try {
            // Cek apakah Alert muncul (misal: "Wrong password")
            // Kita beri waktu singkat (misal 2-3 detik) untuk alert muncul
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.alertIsPresent());

            // JIKA CODE MASUK SINI = ALERT MUNCUL (LOGIN GAGAL)
            Alert alert = driver.switchTo().alert();
            String errorText = alert.getText();
            alert.accept(); // Tutup alert agar browser tidak freeze

            // Lempar error agar TestNG tahu test ini gagal karena salah password
            throw new RuntimeException("Login Gagal! Alert muncul: " + errorText);

        } catch (TimeoutException e) {
            // JIKA CODE MASUK SINI = ALERT TIDAK MUNCUL (LOGIN BERHASIL)
            // Lanjut menunggu modal tertutup
        }

        // 3. Pastikan Login Benar-benar Sukses
        // Tunggu modal hilang
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("logInModal")));

        // Opsional: Tunggu sampai nama user muncul di pojok kanan (tanda pasti sudah login)
        wait.until(ExpectedConditions.visibilityOfElementLocated(loggedUser));
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
