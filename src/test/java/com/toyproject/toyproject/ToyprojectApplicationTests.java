package com.toyproject.toyproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.toyproject.domain.Test1;
import com.toyproject.persistence.TestRepo;

@SpringBootTest
class ToyprojectApplicationTests {
	@Autowired
	private TestRepo tr;
	String line = "";

	public void saveTestData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/data_festival.csv"));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				Test1 t = new Test1();
				t.setName(data[0]);
				tr.save(t);
				

			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Test
	void contextLoads() {
		System.out.println("-- contextLoads");
		saveTestData();
	}

}
