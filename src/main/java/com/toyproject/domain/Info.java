package com.toyproject.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int info_id;
    private String place;
    private Date start;
    private Date end;
    @Column(length = 1024)
    private String content;
    private String content_info;

}
