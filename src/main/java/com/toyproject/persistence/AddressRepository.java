package com.toyproject.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toyproject.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    public boolean existsBySidoAndSigunguAndEupmyunAndDoro(String sido, String sigungu, String eupmyun, String doro);

    public List<Address>  findBySidoAndSigunguAndEupmyunAndDoro(String sido, String sigungu, String eupmyun, String doro);
}
