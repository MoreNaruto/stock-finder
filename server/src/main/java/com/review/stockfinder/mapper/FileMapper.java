package com.review.stockfinder.mapper;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.review.stockfinder.models.Company;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileMapper {
    public static List<Company> convertFileToListOfCompanies(List<String> fileNames) {
        List<Company> companies = new ArrayList<>();
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        fileNames.stream()
                .forEach(fileName -> {
                    Reader reader;
                    try {
                        readFile(companies, systemClassLoader, fileName);
                    } catch (IOException | URISyntaxException | CsvValidationException e) {
                        e.printStackTrace();
                    }
                });


        return companies;
    }

    private static void readFile(List<Company> companies, ClassLoader systemClassLoader, String fileName) throws IOException, URISyntaxException, CsvValidationException {
        Reader reader;
        reader = Files.newBufferedReader(Paths.get(systemClassLoader.getResource(fileName).toURI()));
        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(',')
                        .withIgnoreQuotations(true)
                        .build())
                .build();

        csvReader.readNext();

        String[] attributes;
        while ((attributes = csvReader.readNext()) != null) {
            companies.add(convertRowToCompany(attributes));
        }
    }

    private static Company convertRowToCompany(String[] attributes) {
        return Company.builder()
                .symbol(attributes[0])
                .name(attributes[1])
                .lastSale(attributes[2])
                .marketCap(attributes[3])
                .ipoYear(attributes[4])
                .sector(attributes[5])
                .industry(attributes[6])
                .summaryQuote(attributes[7])
                .build();
    }
}
