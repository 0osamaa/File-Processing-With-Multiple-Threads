# File-Processing-With-Multiple-Threads
 Processing a CSV file into a database using a Spring Boot application. 


Processing a CSV file into a database using a Spring Boot application.

The CSV file contains over 40,000 records of MSISDNs.
For each MSISDN:
-> First, check if the MSISDN exists in the database by executing a SELECT query.
-> Retrieve the current status of the MSISDN from the SELECT query and store it in an object.
-> Update the status of the MSISDN using an UPDATE query.
-> Involve two additional database tables:
-> StatusHistory: Insert the previous status (from the SELECT query) and the updated status using an INSERT query.
-> StatusDetail: At the end of the processing, insert the total number of records in the file and the number of successfully updated records using an INSERT query.

In Total:
There are four types of input/output operations:

SELECT
UPDATE
INSERT (performed twice) 
