package com.toyproject.toyproject;

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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.toyproject.domain.Address;
import com.toyproject.domain.Contact;
import com.toyproject.domain.Festival;
import com.toyproject.domain.Info;
import com.toyproject.persistence.AddressRepository;
import com.toyproject.persistence.ContactRepository;
import com.toyproject.persistence.FestivalRepository;
import com.toyproject.persistence.InfoRepository;

@SpringBootTest
class ToyprojectApplicationTests {
    @Autowired
    private InfoRepository Ir;
    @Autowired
    private AddressRepository Ar;
    @Autowired
    private ContactRepository Cr;
    @Autowired
    private FestivalRepository Fr;

    @Test
    //테스트에서 엑셀파일 불러와서 전처리하고 데이터를 넣은 클래스
    public void ExcelFileRead() {
        try {
            // 절대경로 지정
            String fileLocatioString = "src/main/resources/data_festival_excel.xlsx";
            // 절대경로에서 가져온 파일 변수
            FileInputStream file = new FileInputStream(new File(fileLocatioString));
            //파일을  workbook으로 만드는 함수
            Workbook workbook = new XSSFWorkbook(file);
            //워크북에서 시트를 가져옴
            Sheet sheet = workbook.getSheetAt(0);

            //맵을 사용해서 data객채를 만듬
            Map<Integer, List<String>> data = new HashMap<>();
            int i = 0;
            // 시트를 한줄씩 읽으면서 data에 넣기
            for (Row row : sheet) {
                data.put(i, new ArrayList<String>());

                //시트 max함수를 이용해서 둘중의 큰것으로 배열 크기 맞추기
                int lastColumn = Math.max(row.getLastCellNum(), 14);
               
                //배열을 돌면서 데이터가 있는 칸 속성맞춰주기
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell cell = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    String cellValue;
                    if (cell == null) {
                        // 배열에 빈 스트링이 있으면 null값 넣기
                        cellValue = "";
                    } else {
                        // null값이 아니면 스트링 포맷에 따라서 속성 부여 
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
                                cellValue = ""; // 기타 셀 타입이라면 빈 문자열로 처리
                        }
                    }
                    //data에 add함수를 이용해서 진짜로 넣기
                    data.get(i).add(cellValue);
                }
                // i값 증가시켜서 반복
                i++;
            }

            // 첫번째 줄은 제목이라서 1번 생략하고 데이터 읽기
            for (int j = 1; j < sheet.getLastRowNum(); j++) {
                // 배열의 첫번째부터 데이터 가져오기
                List<String> rowData = data.get(j);
                System.out.println(rowData.size());
            
                // 데이터베이스에 있는 테이블 가져오기
                Info info = new Info();
                Address address = new Address();
                Contact contact = new Contact();
                Festival festival = new Festival();
               
                System.out.println("Padded Row " + j + ": " + rowData);

                // 테이블에 넣을 값을 지정

                info.setPlace(data.get(j).get(1));
                
                System.out.println(data.get(j).get(2));
                // 타입 캐스팅하는 코드
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

                if (data.get(j).get(13) != "") {
                    address.setLatitude(Float.parseFloat(data.get(j).get(13)));
                }
                System.out.println(data.get(j).get(13));
                if (data.get(j).get(14) != "") {
                    address.setLongitude(Float.parseFloat(data.get(j).get(14)));
                }

                System.out.println(data.get(j).get(12));

                // string을 float으로 바꾸고 float을 int로 바꾸고 다시 string으로 타입 캐스팅
                if (data.get(j).get(12) != "") {
                    address.setZip_no(String.format("%d", (int) Float.parseFloat(data.get(j).get(12))));
                }
                // ""으로 split해서 전처리하고 데이블에 넣는값 지정
                if (data.get(j).get(11) != "") {

                    String[] addressDoroStrings = data.get(j).get(11).split(" ");
                    address.setSido(addressDoroStrings[0]);
                    address.setSigungu(addressDoroStrings[1]);
                    // string 값을 " " 잘라서 2번째 부터 끝까지 붙여서  join하게 만듬
                    String addressFromIndex2ToEnd = Arrays.stream(addressDoroStrings, 2, addressDoroStrings.length)
                            .collect(Collectors.joining(" "));
                    address.setDoro(addressFromIndex2ToEnd);
                }
                if (data.get(j).get(10) != "") {
                    String[] addressJibunStrings = data.get(j).get(10).split(" ");
                    if (addressJibunStrings.length >= 3) {
                        address.setEupmyun(addressJibunStrings[2]);
                    }

                    String addressFromIndex3ToEnd = Arrays.stream(addressJibunStrings, 3, addressJibunStrings.length)
                            .collect(Collectors.joining(" "));
                    address.setSangse(addressFromIndex3ToEnd);
                }

                festival.setFestival_name(data.get(j).get(0));

                // Address 클래스에 있는 객채를 list로 변환
                List<Address> addr = null;
                // 데이터 베이스에 있는 지 없는지 확인
                // repository들어가서 확인하는 클래스 만듬 jpa 쿼리메소드 활용
                if (Ar.existsBySidoAndSigunguAndEupmyunAndDoro(address.getSido(), address.getSigungu(),
                        address.getEupmyun(), address.getDoro()) == false) {
                    // 진짜로 repository 저장하는 함수        
                    Ar.save(address);
                    festival.setAddress_id(address.getAddress_id());

                } else {
                    // 중복되는 값을 없에고 포린키 설정을 위해 get으로 addr list에 있는 id 값을 가져와 설정
                    addr = Ar.findBySidoAndSigunguAndEupmyunAndDoro(address.getSido(), address.getSigungu(),
                            address.getEupmyun(), address.getDoro());
                    System.out.println(addr.toString());
                    System.out.println(addr.get(0).getAddress_id());

                    festival.setAddress_id(addr.get(0).getAddress_id());
                }

                Cr.save(contact);
                Ir.save(info);

                festival.setContact_id(contact.getContact_id());
                festival.setInfo_id(info.getInfo_id());
                // 포린키 순서때문에 마지막에 실행
                Fr.save(festival);

            }
            //워크북 닫기
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
