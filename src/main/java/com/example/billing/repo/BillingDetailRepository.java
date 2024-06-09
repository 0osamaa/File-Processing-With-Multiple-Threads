package com.example.billing.repo;

import com.example.billing.Util.Constants;
import com.example.billing.model.BillingDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BillingDetailRepository {

    private final JdbcTemplate jdbcTemplate;

    private final Constants constants;

    public void insertIntoBillingDetail(BillingDetail billingDetail) {
        Integer status = jdbcTemplate.update(constants.getINSERT_BILLING_DETAILS(), billingDetail.getBillingDate(), billingDetail.getHost(),
                billingDetail.getTotalUsers(), billingDetail.getSuccessCount(), billingDetail.getTStamp());
        if (status > 0) {
            log.info("Successfully Inserted record in Billing details");
        } else {
            log.info("Insertion failed in billing details data : {}", billingDetail);
        }
    }
}
