package com.smartindustries.smartlock.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SmartlockPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartlockPlatformApplication.class, args);
    }

}
