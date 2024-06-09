package com.example.billing.Util;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "provgw")
public class Constants {


    private String PREPAIDNORMALREQUEST;
    private String STATUS_FROM_NUMBERMAPPING;
    private String INSERT_INTO_BILLINGHISTORY;

}
