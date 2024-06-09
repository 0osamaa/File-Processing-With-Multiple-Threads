package com.example.billing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class BillingApplication {

    public static void main(String[] args) {
        log.info("--------------Application started-----------------");
        SpringApplication.run(BillingApplication.class, args);
    }
}
