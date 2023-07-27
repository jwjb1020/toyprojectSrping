package com.toyproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue
    private int address_id;
    private String sido;
    private String sigungu;
    private String eupmyun;
    private String doro;
    private String sangse;
    private int zip_no;
    private float latitude;
    private float longitude;
    private float x;
    private float y;
}
