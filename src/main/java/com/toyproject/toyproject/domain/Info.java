package com.toyproject.toyproject.domain;

import java.util.Date;

import jakarta.persistence.Entity;
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
    private int info_id;
    private String place;
    private Date start;
    private Date end;
    private String content;
    private String content_info;

}
