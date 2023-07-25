package com.toyproject.toyproject;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.opencsv.exceptions.CsvValidationException;
import com.toyproject.toyproject.domain.Festival;
import com.toyproject.toyproject.persistence.FestivalRepository;

import service.CsvReaderUtil;

@SpringBootApplication
public class ToyprojectApplication {

	public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ToyprojectApplication.class, args);

        String csvFilePath = "C:\\ToyProject/data_festival.csv";

        try {
            List<Festival> festivalDataList = CsvReaderUtil.readCsvFile(csvFilePath);

            // Get the FestivalRepository bean from ApplicationContext
            FestivalRepository festivalRepository = context.getBean(FestivalRepository.class);

            festivalRepository.saveAll(festivalDataList);
            System.out.println("CSV data imported successfully.");
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error importing CSV data: " + e.getMessage());
        }
    }

}
