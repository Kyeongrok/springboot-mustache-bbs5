package com.mustache.bbs5.repository;

import com.mustache.bbs5.domain.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    List<Hospital> findByHospitalName(String hospitalName);
    List<Hospital> findByHospitalNameStartingWith(String str);
    List<Hospital> findByHospitalNameContaining(String str);

    List<Hospital> findByRoadNameAddressStartingWith(String str);
    List<Hospital> findByRoadNameAddressContaining(String str);
    Page<Hospital> findByRoadNameAddressContaining(String str, Pageable pageable);
    List<Hospital> findByHospitalNameLike(String str);
    List<Hospital> findByRoadNameAddressLike(String str);

    List<Hospital> findByTotalAreaSizeBetween(float a, float b);


    List<Hospital> findByRoadNameAddressContainingOrderByTotalAreaSize(String str);
    // order

}
