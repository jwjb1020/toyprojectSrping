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
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Acommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int acommodation_id;
    private int contact_id;
    private int address_id;
    private String name;
    private int aco_id;
    private int building_id;

}
