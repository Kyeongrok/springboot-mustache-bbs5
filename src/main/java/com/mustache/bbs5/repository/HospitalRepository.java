package com.mustache.bbs5.repository;

import com.mustache.bbs5.domain.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {


    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
    List<Hospital> findByRoadNameAddressContaining(String keyword);
    List<Hospital> findByHospitalNameStartsWith(String keyword);
    List<Hospital> findByHospitalNameEndsWith(String keyword);


    List<Hospital> findByPatientRoomCountGreaterThanAndPatientRoomCountLessThan(int var1, int var2);

    List<Hospital> findByPatientRoomCountBetween(int a, int b);

    List<Hospital> findByPatientRoomCountBetweenOOrderByPatientRoomCountDesc(int a, int b);



    List<Hospital> findByHospitalName(String hospitalName);
    List<Hospital> findByHospitalNameStartingWith(String str);
    List<Hospital> findByHospitalNameContaining(String str);

    List<Hospital> findByRoadNameAddressStartingWith(String str);
    Page<Hospital> findByRoadNameAddressContaining(String str, Pageable pageable);
    List<Hospital> findByHospitalNameLike(String str);
    List<Hospital> findByRoadNameAddressLike(String str);

    List<Hospital> findByTotalAreaSizeBetween(float a, float b);


    List<Hospital> findByRoadNameAddressContainingOrderByTotalAreaSize(String str);
    // order

}
