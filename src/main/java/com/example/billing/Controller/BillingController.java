package com.example.billing.Controller;


import com.example.billing.Service.BillingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/billing")
@Slf4j
@RequiredArgsConstructor
public class BillingController {

    private final BillingService billingService;


    @PostMapping("/upload")
    public String uploadCSV(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("--------------Process Billing Started Via API-----------------");
        if (file.isEmpty()) {
            return "BAD REQUEST";
        }
        billingService.processFileViaApi(file);
        return "PROCESSING";
    }
}
