package com.example.billing.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class BillingDetail {

    private Date BillingDate;
    private String Host;
    private Integer TotalUsers;
    private Integer SuccessCount;
    private Date TStamp;

}
