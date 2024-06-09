package com.example.billing.Service;

import com.example.billing.Util.BillingUtil;
import com.example.billing.Util.Constants;
import com.example.billing.model.NumberMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.concurrent.RecursiveAction;


@Slf4j
public class LineProcessor extends RecursiveAction {

    private String line;
    private JdbcTemplate jdbcTemplate;
    private Constants constants;
    private List<String> failedMsisdns;

    @Autowired
    private BillingUtil billingUtil; // Reference to the main class for synchronization and method calls

    @Autowired
    private BillingService billingService;

    public LineProcessor(String line, JdbcTemplate jdbcTemplate, Constants constants, List<String> failedMsisdns, BillingService billingService) {
        this.line = line;
        this.jdbcTemplate = jdbcTemplate;
        this.constants = constants;
        this.failedMsisdns = failedMsisdns;
        this.billingService = billingService;
    }

    @Override
    protected void compute() {
        try {
            String[] columns = line.split("\\|");
            if (columns.length > 0) {
                String number = columns[0];
                String billedDate = columns[1].substring(0, 14);

                NumberMapping numberMapping = billingService.getStatusFromNumberMapping(number); // SELECT query from NumberMapping Table

                try {
                    String query = constants.getUPDATE_NUMBER_MAPPING()
                            .replace("{BILLEDUPTO}", billedDate)
                            .replace("{TELCOMSISDN}", number);
                    int updatedRows = jdbcTemplate.update(query); // UPDATE query for NumberMapping Table
                    if (updatedRows > 0) {
                        billingService.incrementSuccessfulUpdates();
                        if (numberMapping != null) {
                            billingService.insertIntoBillingHistory(number, numberMapping.getStatus(), numberMapping.getTelcoType(), numberMapping.getTypeOfNum()); // INSERT query for BillingHistory Table
                        }
                    } else {
                        failedMsisdns.add(number);
                    }
                } catch (Exception e) {
                    log.error("Exception occurred while updating number {}: {}", number, e.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Exception occurred while processing line: {}", e.getMessage());
        }
    }
}