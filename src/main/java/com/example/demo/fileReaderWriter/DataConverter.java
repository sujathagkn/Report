package com.example.demo.fileReaderWriter;

import com.example.demo.config.InputConfiguration;
import com.example.demo.config.InputDetails;
import com.example.demo.model.DailyReportRow;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

/**
 * Created by sujatha.bandi on 5/8/18.
 */
@Component
public class DataConverter {
    private final InputConfiguration inputConfiguration;

    public DataConverter(InputConfiguration inputConfiguration) {
        this.inputConfiguration = inputConfiguration;
    }

    /*
        The CSV has the following Headers
    - Client_Information
    - Product_Information
    - Total_Transaction_Amount

    Client_Information should be a combination of the CLIENT_TYPE, CLIENT NUMBER, ACCOUNT NUMBER, SUBACCOUNT NUMBER fields from Input file
    Product_Information should be a combination of the EXCHANGE CODE, PRODUCT GROUP CODE, SYMBOL, EXPIRATION DATE
    Total_Transaction_Amount should be a Net Total of the (QUANTITY LONG - QUANTITY SHORT) values for each client per product
     */
    public DailyReportRow createReportRow(String line) {
        DailyReportRow dailyReportRow = new DailyReportRow();
        StringJoiner clientInformation = new StringJoiner("_");
        clientInformation = clientInformation.add(getFeild(line, "CLIENT_TYPE"));
        clientInformation = clientInformation.add(getFeild(line, "CLIENT_NUMBER"));
        clientInformation = clientInformation.add(getFeild(line, "ACCOUNT_NUMBER"));
        clientInformation = clientInformation.add(getFeild(line, "SUBACCOUNT_NUMBER"));

        StringJoiner productInformation = new StringJoiner("_");
        productInformation = productInformation.add(getFeild(line, "EXCHANGE_CODE"));
        productInformation = productInformation.add(getFeild(line, "PRODUCT_GROUP_CODE"));
        productInformation = productInformation.add(getFeild(line, "SYMBOL"));
        productInformation = productInformation.add(getFeild(line, "EXPIRATION_DATE"));

        long totalTransactionAmount = Long.parseLong(getFeild(line, "QUANTITY_LONG").toString()) -
            Long.parseLong(getFeild(line, "QUANTITY_SHORT").toString()) ;


        dailyReportRow.setClientInformation(clientInformation.toString());
        dailyReportRow.setProductInformation(productInformation.toString());
        dailyReportRow.setClientProdKey(getFeild(line, "CLIENT_NUMBER") + "_" +
            getFeild(line, "PRODUCT_GROUP_CODE")) ;
        dailyReportRow.setTotalTransactionAmount(String.valueOf(totalTransactionAmount));

        return dailyReportRow;
    }

    private CharSequence  getFeild(String line, String fieldName) {
        InputDetails inputDetails = inputConfiguration.getDataOptions().get(fieldName);
        return TextReader.getFiled(line, inputDetails.getIndex(), inputDetails.getSize());
    }
}
