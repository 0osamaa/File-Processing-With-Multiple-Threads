
package com.example.billing.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class BillingHistory {

    private String originTransactionID;
    private String subscriberNumber;
    private Integer previousStatus;
    private Integer newStatus;
    private String originNodeType;
    private String originHostName;
    private Date originTimeStamp;
    private String transactionCurrency;
    private String adjustmentAmountRelative;
    private String transactionCode;
    private String transactionResponse;
    private String waridHost;
    private Date tStamp = new Date();
}
