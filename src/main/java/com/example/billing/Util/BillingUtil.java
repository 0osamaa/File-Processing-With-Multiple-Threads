package com.example.billing.Util;


import com.example.billing.model.BillingDetail;
import com.example.billing.repo.BillingDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class BillingUtil {

    private final BillingDetailRepository billingDetailRepository;

    public void insertIntoBillingDetail(Date BillingDate,
                                        String Host,
                                        Integer TotalUsers,
                                        Integer SuccessCount,
                                        Date TStamp) {
        BillingDetail billingDetail = new BillingDetail();
        billingDetail.setBillingDate(BillingDate);
        billingDetail.setHost(Host);
        billingDetail.setSuccessCount(SuccessCount);
        billingDetail.setTotalUsers(TotalUsers);
        billingDetail.setTStamp(TStamp);
        billingDetailRepository.insertIntoBillingDetail(billingDetail);
    }

}
