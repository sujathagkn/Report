package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sujatha.bandi on 5/8/18.
 */
@Getter
@Setter
public class DailyReportRow
{
    private String clientInformation;
    private String productInformation;
    private String clientProdKey;
    private String totalTransactionAmount;
}
