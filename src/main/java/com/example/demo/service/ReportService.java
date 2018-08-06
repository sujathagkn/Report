package com.example.demo.service;

import com.example.demo.fileReaderWriter.DataConverter;
import com.example.demo.fileReaderWriter.TextReader;
import com.example.demo.model.DailyReportRow;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sujatha.bandi on 5/8/18.
 */
@Service
public class ReportService {

    private static String header = "Client_Information,Product_Information,Client_Prod_Key,Total_Transaction_Amount";
    private static String header_total = "Prod_Id,Total";

    private final DataConverter dataConverter;

    public ReportService(DataConverter dataConverter) {
        this.dataConverter = dataConverter;
    }


    public void generateReport(String filePath, String outPutDir) throws IOException {

        HashMap<String, Long>  prodTrascactionTotal = new HashMap<>();
        FileWriter writer = new FileWriter(outPutDir  + File.separator +  "output.csv");

        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        csvPrinter.printRecord(header.split(","));


        TextReader textReader = new TextReader();
        ArrayList<String> fileLines = textReader.readLines(filePath);



        for(String line : fileLines) {
            DailyReportRow dailyReportRow = dataConverter.createReportRow(line);
            csvPrinter.printRecord(dailyReportRow.getClientInformation(), dailyReportRow.getProductInformation(),
                dailyReportRow.getClientProdKey(), dailyReportRow.getTotalTransactionAmount());
            if(prodTrascactionTotal.containsKey(dailyReportRow.getClientProdKey())) {
                Long existingTotal = prodTrascactionTotal.get(dailyReportRow.getClientProdKey());
                prodTrascactionTotal.put(dailyReportRow.getClientProdKey(), existingTotal + Long.parseLong(dailyReportRow.getTotalTransactionAmount()));
            } else {
                prodTrascactionTotal.put(dailyReportRow.getClientProdKey(),  Long.parseLong(dailyReportRow.getTotalTransactionAmount()));
            }
        }

        createProdTrascactionCsv(outPutDir, prodTrascactionTotal);
        csvPrinter.flush();
        csvPrinter.close();

       // DailyReportRow dailyReportRow = dataConverter.createReportRow(line);
    }

    private void createProdTrascactionCsv(String outPutDir, HashMap<String, Long>  prodTrascactionTotal) throws IOException {
        FileWriter writer = new FileWriter(outPutDir  + File.separator +  "prodTransaction.csv");

        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        csvPrinter.printRecord(header_total.split(","));

        for(String prodKey :prodTrascactionTotal.keySet()) {
            csvPrinter.printRecord(prodKey, prodTrascactionTotal.get(prodKey));
        }

        csvPrinter.flush();
        csvPrinter.close();
    }
}
