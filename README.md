# 🚀 Demoblaze E-Commerce Automation Framework

![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=java)
![Selenium](https://img.shields.io/badge/Selenium-4.11-green?style=for-the-badge&logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-7.x-red?style=for-the-badge&logo=testng)
![Build](https://img.shields.io/badge/Build-Maven-blue?style=for-the-badge&logo=apachemaven)

## 📋 Overview (Ringkasan)
Repository ini berisi **Test Automation Framework** untuk pengujian *End-to-End (E2E)* pada website e-commerce [Demoblaze](https://www.demoblaze.com/).

Project ini dibangun menggunakan **Java** dan **Selenium WebDriver**, serta menerapkan design pattern **Page Object Model (POM)**. Tujuannya adalah untuk memastikan stabilitas fitur utama (Regression Testing) seperti Login, Cart Management, dan Checkout Flow.

## ✨ Key Features (Fitur Unggulan)
* **Page Object Model (POM):** Struktur kode yang memisahkan logika tes (`src/test`) dengan elemen UI (`src/main`), membuat kode lebih rapi dan mudah di-*maintain*.
* **Dynamic User Data:** Menggunakan *timestamp* (`System.currentTimeMillis`) untuk registrasi user baru di setiap tes, mencegah error *"User already exists"*.
* **Smart Wait & Retry Logic:** Implementasi `Explicit Wait` dan penanganan otomatis `StaleElementReferenceException` (mekanisme *retry click*) untuk mengatasi elemen dinamis.
* **Alert Handling:** Menangani *Pop-up/Alert* browser secara otomatis (baik untuk sukses maupun error validation).
* **Negative & Positive Testing:** Mencakup skenario sukses (*Happy Path*) dan skenario gagal (validasi form kosong).

## 🛠️ Tech Stack
* **Language:** Java JDK 25
* **Automation Tool:** Selenium WebDriver
* **Test Runner:** TestNG
* **Build Tool:** Apache Maven
* **IDE:** IntelliJ IDEA / VS Code

## 📂 Project Structure
```text
src
├── main
│   └── java
│       └── com.praktikum.rest.pages  # Page Objects (Menyimpan Locators & Actions)
│           ├── HomePage.java
│           ├── LoginPage.java
│           ├── ProductPage.java
│           ├── CartPage.java
│           └── CheckoutPage.java
└── test
    └── java
        └── com.praktikum.rest
            ├── base                  # Base Test (Setup Driver & Teardown)
            │   └── BaseTest.java
            └── tests                 # Test Classes (Skenario Pengujian)
                ├── CheckoutFlowTest.java      # Negative Case Validation
                ├── CompletePurchaseTest.java  # Positive E2E Flow
                └── DeleteFromCart.java        # Cart Functionality

```
## 🚀 How to Run (Cara Menjalankan)

### Prerequisites
Pastikan kamu sudah menginstal:
* Java JDK (Minimal versi 11, rekomendasi JDK 17/25)
* Maven
* Google Chrome Browser

### Installation
1.  **Clone repository ini:**
    ```bash
    git clone https://github.com/Zagarap/Uas_demoblaze.git
    ```
2.  **Masuk ke folder project:**
    ```bash
    cd Uas_demoblaze
    ```
3.  **Install Dependencies:**
    ```bash
    mvn clean install -DskipTests
    ```

### Running Tests
Untuk menjalankan semua skenario tes secara otomatis:
```bash
mvn clean test

## 📊 Test Scenarios Covered

| Class Name | Scenario Description | Type | Status |
| :--- | :--- | :--- | :--- |
| **CheckoutFlowTest** | Validasi error alert saat user checkout tanpa mengisi data formulir. | Negative | ✅ PASS |
| **CompletePurchaseTest** | Simulasi user registrasi, login, pilih kategori Laptop, add to cart, hingga sukses bayar. | Positive | ✅ PASS |
| **DeleteFromCart** | Validasi fitur menghapus item dari keranjang belanja. | Functional | ✅ PASS |

## 👤 Author
**Ihza Awaludin**
* Mahasiswa Politeknik Negeri Cilacap
* QA Engineer Enthusiast

---
