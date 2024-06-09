package com.example.billing.Service;


import com.example.billing.Util.BillingUtil;
import com.example.billing.Util.Constants;
import com.example.billing.model.NumberMapping;
import com.example.billing.model.UcipConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;

@Service
@Slf4j
@RequiredArgsConstructor
public class BillingService {

    private final JdbcTemplate jdbcTemplate;

    private final BillingUtil billingUtil;
    private final Constants constants;
    private final BillingHistoryService billingHistoryService;

    private final UcipConfig ucipConfig;
    private final List<String> failedMsisdns = new CopyOnWriteArrayList<>(); // Thread-safe list for failed MSISDNs
    private int totalMSISDN = 0;
    private int successfulUpdates = 0;

    public void processFileViaApi(MultipartFile file) throws IOException {
        Date billingStarted = new Date();
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        try {
            ForkJoinPool forkJoinPool = new ForkJoinPool(ucipConfig.getPOOLSIZE());
            String line;

            while ((line = br.readLine()) != null) {
                totalMSISDN++;
                forkJoinPool.execute(new LineProcessor(line, jdbcTemplate, constants, failedMsisdns, this));
            }

            forkJoinPool.shutdown();
            forkJoinPool.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.MILLISECONDS);

            log.info("TOTAL MSISDN's --> {}", totalMSISDN);
            log.info("ROWS UPDATED --> {}", successfulUpdates);
            log.info("FAILED MSISDN's --> {}", failedMsisdns);
        } catch (Exception e) {
            log.error("Exception occurred while processing file: {}", e.getMessage());
        } finally {
            billingUtil.insertIntoBillingDetail(billingStarted, constants.getPREPAIDNORMALREQUEST(), totalMSISDN, successfulUpdates, new Date());
            log.info("--------------Process Billing ShutDown after executing PathFile-----------------");
            totalMSISDN = 0;
            failedMsisdns.clear();
            successfulUpdates = 0;
        }
    }

    public synchronized void incrementSuccessfulUpdates() {
        successfulUpdates++;
    }

    public NumberMapping getStatusFromNumberMapping(String number) {
        NumberMapping numberMapping = null;

        try {
            numberMapping = jdbcTemplate.queryForObject(constants.getSTATUS_FROM_NUMBERMAPPING()
                            .replace("{TELCOMSISDN}", number),
                    new BeanPropertyRowMapper<>(NumberMapping.class));
        } catch (Exception e) {
            log.info("Record not found against the user :{}", number);
        }
        return numberMapping;
    }

    public void insertIntoBillingHistory(String number, Integer status, Integer telcoType, Integer typeOfNum) {
        billingHistoryService.billingHistory(number, status, 1, "originNodeType", "originHostName", new Date(), "PKR", "251", "transactionCode", "0000", "waridHost", generateUUID(number));
    }


    public String generateUUID(String number) {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();
        String result = number + uuid.toString().substring(0, 8);
        return result;
    }

}
