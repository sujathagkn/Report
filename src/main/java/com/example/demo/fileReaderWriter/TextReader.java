package com.example.demo.fileReaderWriter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by sujatha.bandi on 5/8/18.
 */
@Slf4j
public class TextReader {

    public ArrayList<String> readLines(String filePath) {
        ArrayList<String> lines = new ArrayList<>();
        try {
             Files.lines(Paths.get(filePath)).forEach(s -> lines.add(s));
        } catch (IOException e) {
            log.error("Error reading file:" + filePath);
        };
        return lines;
    }

    public static String getFiled(String line, int index, int size) {
        try {
        return line.substring(index, index + size); }
        catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }
}
