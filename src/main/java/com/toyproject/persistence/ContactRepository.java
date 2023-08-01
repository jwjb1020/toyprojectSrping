package com.toyproject.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toyproject.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    public boolean existsByHomepageAndTelAndJugwan(String homepage, String tel, String jugwan);

    public List<Contact>  findByHomepageAndTelAndJugwan(String homepage, String tel, String jugwan);
}
