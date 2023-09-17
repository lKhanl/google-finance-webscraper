package dev.oguzhanercelik.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OSDriverConfiguration {

    @Bean
    public void osDriver() {
        final String os = System.getProperty("os.name");
        if (os.equals("Mac OS X")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver-mac-arm64/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver-win64/chromedriver");
        }
    }

}
