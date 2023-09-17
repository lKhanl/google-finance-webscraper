package dev.oguzhanercelik;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class Controller {

    private static final int WAITING_TIME = 1000;

    @GetMapping
    public String getValue(@RequestParam String value) {

        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        final WebDriver webDriver = new ChromeDriver(options);

        webDriver.get("https://www.google.com/finance/quote/" + value.toUpperCase(Locale.ENGLISH) + ":IST");

        try {
            Thread.sleep(WAITING_TIME);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        final WebElement element = webDriver.findElement(By.cssSelector("div.YMlKec.fxKbKc"));
        final String val = element.getText().substring(1);

        webDriver.close();

        return val;
    }

    @GetMapping("/all")
    public String getValues(@RequestParam String values) {
        String[] split = values.split(",");
        List<String> qs = Arrays.asList(split);

        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        final WebDriver webDriver = new ChromeDriver(options);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < qs.size(); i++) {
            webDriver.get("https://www.google.com/finance/quote/" + qs.get(i).toUpperCase(Locale.ENGLISH) + ":IST");

            try {
                Thread.sleep(WAITING_TIME);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            final WebElement element = webDriver.findElement(By.cssSelector("div.YMlKec.fxKbKc"));
            final String val = element.getText().substring(1);
            result.append(val);
            if (qs.size() != i + 1) {
                result.append(";");
            }
        }

        webDriver.close();

        return result.toString();
    }

}
