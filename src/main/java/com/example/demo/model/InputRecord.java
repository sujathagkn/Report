package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sujatha.bandi on 5/8/18.
 */
@Getter
@Setter
public class InputRecord {
    private String recordCode;
    private String clientType;
    private String clientNumber;
    private String accountNumber;
    private String subAccountNumber;
    private String oppositePartyCode;
    private String productGroupCode;
    private String exchangeCode;
    private String symbol;
    private String expirationDate;
    private String currencyCode;
    private String movementCode;
    private String buySellCode;
    private String quantityLongSign;
    private String quantityLong;
    private String quantityShortSign;
    private String quantityShort;
    private String exchBrokerFeeDec;
    private String exchBrokerFeeDc;
    private String exchBrokerFeeCurCode;
    private String clearingFeeDec;
    private String clearingFeeDc;
    private String clearingFeeCurCode;
    private String commission;
    private String commissionDc;
    private String commissionCurCode;
    private String trascactionDate;
    private String futureReference;
    private String tixketNumber;
    private String externalNumber;
    private String transactionPriceDec;
    private String traderDetails;
    private String oppositeTrderId;
    private String openCloseCode;
    private String filter;
}
