package com.toyproject.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toyproject.domain.Info;

public interface InfoRepository extends JpaRepository<Info, Integer> {
    
}
