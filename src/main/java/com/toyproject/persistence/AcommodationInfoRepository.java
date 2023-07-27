package com.toyproject.persistence;

import org.springframework.data.jpa.repository.JpaRepository;


import com.toyproject.domain.AcommodationInfo;

public interface AcommodationInfoRepository extends JpaRepository<AcommodationInfo, Integer> {
    
}
