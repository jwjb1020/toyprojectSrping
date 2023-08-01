package com.toyproject.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

//csv파일을 읽어서 db에 저장하는 서비스 클래스

import org.springframework.stereotype.Service;

import com.toyproject.domain.Address;
import com.toyproject.domain.Contact;
import com.toyproject.domain.Festival;
import com.toyproject.domain.Info;
import com.toyproject.persistence.AddressRepository;
import com.toyproject.persistence.ContactRepository;
import com.toyproject.persistence.FestivalRepository;
import com.toyproject.persistence.InfoRepository;

@Service
public class FestivalService {
    // @Autowired
    // private FestivalRepository Fr;
    @Autowired
    private InfoRepository Ir;
    @Autowired
    private AddressRepository Ar;
    @Autowired
    private ContactRepository Cr;

    String line = "";

    // public void saveFestivalData() {
    //     try {
    //         BufferedReader br = new BufferedReader(
    //                 new InputStreamReader(new FileInputStream("src/main/resources/data_festival.csv"), "UTF8"));
    //         br.readLine();
    //         int number = 0;
    //         while ((line = br.readLine()) != null) {
    //             number += 1;
    //             System.out.println("number" + number);
    //             System.out.println(line);
    //             String[] data = line.split(",");
    //             // Festival festival = new Festival();
    //             Info info = new Info();
    //             Address address = new Address();
    //             Contact contact = new Contact();

    //             int dataLength = data.length;

    //             // festival.setFestival_name(data[0]);
    //             if (dataLength > 15) {
    //                 String contentarray = "";
    //                 for (int i = 4; i < data.length - 10; i++) {
    //                     System.out.println("for" + i);
    //                     contentarray += data[i] + ",";

    //                 }
    //                 info.setContent(contentarray);
    //             } else {
    //                 info.setContent(data[4]);
    //             }

    //             info.setPlace(data[1]);
    //             SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    //             Date dateStart = null;
    //             Date dateEnd = null;
    //             try {
    //                 dateStart = format.parse(data[2]);
    //                 dateEnd = format.parse(data[3]);
    //             } catch (ParseException e) {
    //                 // TODO Auto-generated catch block
    //                 e.printStackTrace();
    //             }
    //             // unhandle exception이 나오면 try catch로 싸기
    //             // exception에 걸리면 변수에 아무것도 없는 거니까 null 먼저 선언

    //             info.setStart(dateStart);
    //             info.setEnd(dateEnd);
    //             info.setContent_info(data[dataLength - 7]);
    //             System.out.println(data[dataLength - 5]);
    //             String [] list = data[dataLength-5].split(" ");
    //             System.out.println(list.length);
                

    //             if (data[dataLength - 5].equals("")) {
    //                 System.out.println("sss");
    //             } else if (list.length == 4) {
    //                 System.out.println(list.length);
    //                 String addArraysData = data[dataLength - 6].replace(",", "/");
    //                 System.out.println(addArraysData);
    //                 String[] addArrays = addArraysData.split(" ");

    //                 address.setSido(addArrays[0]);
    //                 address.setSigungu(addArrays[1]);

    //             } else if (list.length > 0) {

    //                 System.out.println(list.length);
    //                 System.out.println(data[dataLength - 6]);
    //                 String addArraysData = (data[dataLength-7]+data[dataLength - 6]).replace(",", "/");
    //                 System.out.println(addArraysData);
    //                 String[] addArrays = addArraysData.split(" ");
    //                 String sangseString = "";
    //                 for (int j = 3; j < addArrays.length; j++) {
    //                     sangseString += addArrays[j] + " ";

    //                 }
    //                 address.setSido(addArrays[0]);
    //                 address.setSigungu(addArrays[1]);
    //                 address.setEupmyun(addArrays[2]);
    //                 address.setSangse(sangseString);
    //             } else if (list.length == 1) {
    //                 String addArraysData = data[dataLength - 5];
    //                 System.out.println(addArraysData);
    //                 String[] addArrays = data[dataLength - 5].split(" ");
    //                 String sangseString = "";
    //                 for (int j = 3; j < addArrays.length; j++) {
    //                     sangseString += addArrays[j] + " ";

    //                 }
    //                 address.setSido(addArrays[0]);
    //                 address.setSigungu(addArrays[1]);
    //                 address.setEupmyun(addArrays[2]);
    //                 address.setSangse(sangseString);
    //             }
    //             String[] addArrays2 = data[dataLength - 4].split(" ");

    //             address.setDoro(addArrays2[addArrays2.length - 1]);

    //             System.out.println(data[dataLength - 2]);
    //             address.setLatitude(Float.parseFloat(data[dataLength - 2]));
    //             System.out.println(data[dataLength - 1]);
    //             address.setLongitude(Float.parseFloat(data[dataLength - 1]));
    //             System.out.println(data[dataLength - 3]);
    //             if (!data[dataLength - 3].equals("")) {
    //                 address.setZip_no(Integer.parseInt(data[dataLength - 3]));
    //             }

    //             System.out.println(data[dataLength - 8]);
    //             contact.setHomepage(data[dataLength - 8]);
    //             contact.setTel(data[dataLength - 9]);
    //             contact.setJugwan(data[dataLength - 10]);

    //             // Fr.save(festival);
    //             Ir.save(info);
    //             Ar.save(address);
    //             Cr.save(contact);

    //         }
    //         br.close();
    //     } catch (IOException e) {

    //         e.printStackTrace();
    //     }

    // }
}
