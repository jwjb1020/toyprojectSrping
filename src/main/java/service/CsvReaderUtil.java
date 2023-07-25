package service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.toyproject.toyproject.domain.Festival;

public class CsvReaderUtil {

    public static List<Festival> readCsvFile(String csvFilePath) throws IOException, CsvValidationException {
        List<Festival> festivalDataList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] line;
            reader.skip(1); // Skip header line
            while ((line = reader.readNext()) != null) {
                Festival festivalData = new Festival();
                festivalData.setFestival_name(line[0]);
                /* festivalData.setLocation(line[1]);
                festivalData.setStartDate(LocalDate.parse(line[2], DateTimeFormatter.ISO_LOCAL_DATE));
                festivalData.setEndDate(LocalDate.parse(line[3], DateTimeFormatter.ISO_LOCAL_DATE));
                festivalData.setContent(line[4]);
                festivalData.setOrganization(line[5]);
                festivalData.setTelephone(line[6]);
                festivalData.setHomepage(line[7]);
                festivalData.setRelatedInfo(line[8]);
                festivalData.setAddress(line[9]);
                festivalData.setJibunAddress(line[10]);
                festivalData.setRoadAddress(line[11]);
                festivalData.setZipCode(line[12]); */
                festivalDataList.add(festivalData);
            }
        }
        return festivalDataList;
    }
}

