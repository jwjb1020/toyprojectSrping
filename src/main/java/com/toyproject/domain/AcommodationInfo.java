package com.toyproject.domain;

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
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AcommodationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aco_id;
    private String aco_class;
    private int hansil;
    private int yangsil;

}
