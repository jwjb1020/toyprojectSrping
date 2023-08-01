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
@NoArgsConstructor
@AllArgsConstructor
@Entity


public class Festival {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int festival_id;
    private int contact_id;
    private int address_id;
    private String festival_name;
    private int info_id;
    
}
