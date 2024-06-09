/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.billing.repo;

import com.example.billing.Util.Constants;
import com.example.billing.model.BillingHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
@RequiredArgsConstructor
public class BillingHistoryRepository {

    private final JdbcTemplate jdbcTemplate;

    private final Constants constants;

    public void insertIntoBillingHistory(BillingHistory billingHistory) {

        Integer status = jdbcTemplate.update(constants.getINSERT_INTO_BILLINGHISTORY(),
                billingHistory.getSubscriberNumber(),
                billingHistory.getPreviousStatus(),
                billingHistory.getNewStatus(),
                billingHistory.getOriginNodeType(),
                billingHistory.getOriginHostName(),
                billingHistory.getOriginTimeStamp(),
                billingHistory.getTransactionCurrency(),
                billingHistory.getAdjustmentAmountRelative(),
                billingHistory.getTransactionCode(),
                billingHistory.getTransactionResponse(),
                billingHistory.getWaridHost(),
                billingHistory.getOriginTransactionID(),
                billingHistory.getTStamp());
        if (status <= 0) {
            log.info("Insertion failed in billing history");
        }
    }
}
