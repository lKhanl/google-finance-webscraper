package dev.oguzhanercelik;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class GoogleFinanceWebscraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoogleFinanceWebscraperApplication.class, args);
    }

}
