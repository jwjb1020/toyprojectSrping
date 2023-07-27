package com.toyproject.persistence;


import org.springframework.data.jpa.repository.JpaRepository;

import com.toyproject.domain.Test1;

public interface TestRepo extends JpaRepository<Test1, Integer>{
    
}
