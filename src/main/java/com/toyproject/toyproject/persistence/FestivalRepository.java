package com.toyproject.toyproject.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.toyproject.toyproject.domain.Festival;

public interface FestivalRepository extends JpaRepository<Festival, Integer> {
    // 추가적인 커스텀 쿼리 메서드 등을 선언할 수 있습니다.
}
