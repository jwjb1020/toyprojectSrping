package com.toyproject.service;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toyproject.domain.Address;
import com.toyproject.domain.Contact;
import com.toyproject.domain.Info;
import com.toyproject.persistence.AddressRepository;
import com.toyproject.persistence.ContactRepository;
import com.toyproject.persistence.InfoRepository;

@Service

public class FestivalServiceExel {
    @Autowired
    private InfoRepository Ir;
    @Autowired
    private AddressRepository Ar;
    @Autowired
    private ContactRepository Cr;

    // exel파일 읽는 코드
    public void ExcelFileRead() {
        try {
            String fileLocatioString = "src/main/resources/data_festival_excel.xlsx";
            FileInputStream file = new FileInputStream(new File(fileLocatioString));
            Workbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(0);

            Map<Integer, List<String>> data = new HashMap<>();
            int i = 0;

            for (Row row : sheet) {
                data.put(i, new ArrayList<String>());

                int lastColumn = Math.max(row.getLastCellNum(), 14);

                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell cell = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    String cellValue;
                    if (cell == null) {
                        // The spreadsheet is empty in this cell
                        cellValue = "dsafsadf";
                    } else {
                        // Do something useful with the cell's contents
                        cellValue = null;
                        switch (cell.getCellType()) {
                            case STRING:
                                String strValue = cell.getRichStringCellValue().getString();
                                cellValue = strValue.isEmpty() ? "" : strValue;
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    cellValue = cell.getDateCellValue() + "";
                                } else {
                                    cellValue = cell.getNumericCellValue() + "";
                                }
                                break;
                            case BOOLEAN:
                                cellValue = cell.getBooleanCellValue() + "";
                                break;
                            case FORMULA:
                                cellValue = cell.getCellFormula() + "";
                                break;
                            default:
                                cellValue = "dsafsadf"; // 기타 셀 타입이라면 빈 문자열로 처리
                        }
                    }

                    // 빈 셀일 경우에도 배열에 빈 문자열을 추가하여 크기를 유지
                    data.get(i).add(cellValue);
                }

                i++;
            }
            Info info = new Info();
            Address address = new Address();
            Contact contact = new Contact();
            // System.out.println(data.get(0).get(1));/
            for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                List<String> rowData = data.get(j);
                System.out.println(rowData.size());
                // if (rowData == null) {
                // // data.get(j)가 null인 경우 해당 행의 처리를 건너뜁니다.
                // continue;
                // }
                // // 데이터가 셀에 없는 경우 빈 문자열을 추가하여 크기를 14로 맞춥니다.
                // while (rowData.size() < 14) {
                // rowData.add(""); // 빈 문자열("")을 추가하여 크기를 14로 맞춥니다.
                // }
                // (optional) 추가한 빈 데이터가 제대로 추가되었는지 확인하는 출력문
                System.out.println("Padded Row " + j + ": " + rowData);
                info.setPlace(data.get(j).get(1));
                // 타입 캐스팅하는 코드
                System.out.println(data.get(j).get(2));
                SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                Date dateStart = null;
                Date dateEnd = null;
                try {
                    dateStart = format.parse(data.get(j).get(2));
                    dateEnd = format.parse(data.get(j).get(3));
                    info.setStart(dateStart);
                    info.setEnd(dateEnd);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                info.setContent(data.get(j).get(4));
                info.setContent_info(data.get(j).get(8));

                contact.setJugwan(data.get(j).get(5));
                contact.setTel(data.get(j).get(6));
                contact.setHomepage(data.get(j).get(7));
                System.out.println(data.get(j).get(13));
                address.setLatitude(Float.parseFloat(data.get(j).get(13)));
                System.out.println("get14 : " + data.get(j).get(14));
                address.setLongitude(Float.parseFloat(data.get(j).get(14)));
                System.out.println(data.get(j).get(12));
                String numericString = data.get(j).get(12);
                // double doubleValue = Double.parseDouble(numericString);
                // int integerValue = (int) Math.floor(doubleValue);
                address.setZip_no(numericString);

                String[] addressDoroStrings = data.get(j).get(11).split(" ");
                address.setSido(addressDoroStrings[0]);
                address.setSigungu(addressDoroStrings[1]);
                String addressFromIndex2ToEnd = Arrays.stream(addressDoroStrings, 2, addressDoroStrings.length)
                        .collect(Collectors.joining(" "));
                address.setDoro(addressFromIndex2ToEnd);

                String[] addressJibunStrings = data.get(j).get(10).split(" ");
                address.setEupmyun(addressJibunStrings[3]);
                String addressFromIndex3ToEnd = Arrays.stream(addressJibunStrings, 3, addressJibunStrings.length)
                        .collect(Collectors.joining(" "));
                address.setSangse(addressFromIndex3ToEnd);

                Ir.save(info);
                Ar.save(address);
                Cr.save(contact);

            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
