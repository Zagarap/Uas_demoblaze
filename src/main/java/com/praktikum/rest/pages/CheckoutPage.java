package com.praktikum.rest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    // --- Locators Form ---
    private final By nameInput = By.id("name");
    private final By countryInput = By.id("country");
    private final By cityInput = By.id("city");
    private final By cardInput = By.id("card");
    private final By monthInput = By.id("month");
    private final By yearInput = By.id("year");

    // Tombol Purchase di bawah form
    private final By purchaseBtn = By.xpath("//button[text()='Purchase']");

    // Pop-up Sukses
    private final By successMessage = By.xpath("//h2[contains(text(), 'Thank you for your purchase!')]");
    private final By okButton = By.xpath("//button[text()='OK']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Mengisi semua field sekaligus
    public void fillOrderForm(String name, String country, String city, String card, String month, String year) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
        driver.findElement(countryInput).sendKeys(country);
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(cardInput).sendKeys(card);
        driver.findElement(monthInput).sendKeys(month);
        driver.findElement(yearInput).sendKeys(year);
    }

    public void clickPurchaseButton() {
        driver.findElement(purchaseBtn).click();
    }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).getText();
    }

    public void clickOk() {
        wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
    }
}