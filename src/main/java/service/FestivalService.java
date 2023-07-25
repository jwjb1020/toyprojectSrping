package service;

//csv파일을 읽어서 db에 저장하는 서비스 클래스
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.exceptions.CsvValidationException;
import com.toyproject.toyproject.domain.Festival;
import com.toyproject.toyproject.persistence.FestivalRepository;

import java.io.IOException;
import java.util.List;

@Service
public class FestivalService {

    private final FestivalRepository festivalRepository;

    @Autowired
    public FestivalService(FestivalRepository festivalRepository) {
        this.festivalRepository = festivalRepository;
    }

    public void saveFestivalDataFromCsv(String csvFilePath) throws IOException, CsvValidationException {
        List<Festival> festivalDataList = CsvReaderUtil.readCsvFile(csvFilePath);
        festivalRepository.saveAll(festivalDataList);
    }
}
