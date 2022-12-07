package com.mustache.bbs5.service;

import com.mustache.bbs5.domain.Hospital;
import com.mustache.bbs5.domain.User;
import com.mustache.bbs5.domain.Visit;
import com.mustache.bbs5.domain.dto.VisitCreateRequest;
import com.mustache.bbs5.exceptions.ErrorCode;
import com.mustache.bbs5.exceptions.HospitalReviewAppException;
import com.mustache.bbs5.repository.HospitalRepository;
import com.mustache.bbs5.repository.UserRepository;
import com.mustache.bbs5.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    public void createVisit(VisitCreateRequest dto, String userName) {

        // hospital이 없을 때 등록불가
        Hospital hospital = hospitalRepository.findById(dto.getHospitalId())
                .orElseThrow(() -> new HospitalReviewAppException(ErrorCode.NOT_FOUNDED, String.format("hospitalId:%s 가 없습니다.", dto.getHospitalId())));

        // user가 없을 때 등록불가
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new HospitalReviewAppException(ErrorCode.USER_NOT_FOUNDED, String.format("%s user가 없습니다.", userName)));

        Visit visit = Visit.builder()
                .user(user)
                .hospital(hospital)
                .disease(dto.getDisease())
                .amount(dto.getAmount())
                .build();
        visitRepository.save(visit);
    }
}
