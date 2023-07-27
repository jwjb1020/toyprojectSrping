package com.toyproject.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toyproject.domain.Acommodation;

public interface AcommodationRepository extends JpaRepository<Acommodation, Integer>{
    
}
