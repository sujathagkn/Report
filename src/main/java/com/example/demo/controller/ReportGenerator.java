package com.example.demo.controller;

import com.example.demo.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by sujatha.bandi on 5/8/18.
 */
@RestController
@RequestMapping("/processReport")
@Slf4j
public class ReportGenerator {

    private final ReportService reportService;

    @Autowired
    public ReportGenerator(ReportService reportService) {
        this.reportService = reportService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity generateReport(@RequestParam(value = "inputFile") String inputFile, @RequestParam(value = "outputDir") String outPutDir) {
        try {
            reportService.generateReport(inputFile, outPutDir);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            log.error("Error while reading or writting file");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch (Exception e) {
            log.error("Error while reading or writting file");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
