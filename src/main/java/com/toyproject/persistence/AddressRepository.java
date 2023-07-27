package com.toyproject.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toyproject.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    
}
