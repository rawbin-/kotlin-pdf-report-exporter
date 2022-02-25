package com.example.demo
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Cookie
import org.openqa.selenium.PrintsPage
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.print.PrintOptions
import java.io.File
import java.io.FileOutputStream
import java.util.*

fun main() {
    WebDriverManager.chromedriver().setup()

    val options = ChromeOptions()
    options.addArguments("--headless")

    val driver = ChromeDriver(options)

    driver.get("https://baidu.com")

    driver.manage().addCookie(Cookie("xxxtoken","tokenvalue",".baidu.com","/", Date(2023,2,2)))

    val pdf = (driver as PrintsPage).print(PrintOptions())

    val fileOutputStream = FileOutputStream(File("selenium-out.pdf"))
    fileOutputStream.write(Base64.getDecoder().decode(pdf.getContent()))
    fileOutputStream.close()

    driver.quit()
}