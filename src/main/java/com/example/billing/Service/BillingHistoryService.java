/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.billing.Service;

import com.example.billing.model.BillingHistory;
import com.example.billing.repo.BillingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BillingHistoryService {

    @Autowired
    private BillingHistoryRepository billingHistoryRepository;


    public void billingHistory(String subscriberNumber,
                               Integer previousStatus,
                               Integer newStatus,
                               String originNodeType,
                               String originHostName,
                               Date originTimeStamp,
                               String transactionCurrency,
                               String adjustmentAmountRelative,
                               String transactionCode,
                               String transactionResponse,
                               String waridHost,
                               String originTransactionId
    ) {
        BillingHistory billingHistory = new BillingHistory();
        billingHistory.setSubscriberNumber(subscriberNumber);
        billingHistory.setPreviousStatus(previousStatus);
        billingHistory.setNewStatus(newStatus);
        billingHistory.setOriginNodeType(originNodeType);
        billingHistory.setOriginHostName(originHostName);
        billingHistory.setOriginTimeStamp(originTimeStamp);
        billingHistory.setTransactionCurrency(transactionCurrency);
        billingHistory.setAdjustmentAmountRelative(adjustmentAmountRelative);
        billingHistory.setTransactionCode(transactionCode);
        billingHistory.setTransactionResponse(transactionResponse);
        billingHistory.setWaridHost(waridHost);
        billingHistory.setOriginTransactionID(originTransactionId);
        billingHistoryRepository.insertIntoBillingHistory(billingHistory);
    }
}
