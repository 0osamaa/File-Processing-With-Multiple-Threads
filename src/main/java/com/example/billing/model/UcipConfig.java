package com.example.billing.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "ucip")
public class UcipConfig {
    private Map<String, String> prepaidNormalRequest;
    private Map<String, String> prepaidNormalConfigs;
    private Map<String, String> postpaidNormalRequest;
    private Map<String, String> postpaidNormalConfigs;
    private Map<String, String> prepaidGoldenRequest;
    private Map<String, String> prepaidGoldenConfigs;
    private Integer POOLSIZE;
}
