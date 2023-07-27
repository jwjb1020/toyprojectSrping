package com.toyproject.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toyproject.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    
}
