package com.mustache.bbs5.service;

import com.mustache.bbs5.domain.Disease;
import com.mustache.bbs5.domain.Hospital;
import com.mustache.bbs5.domain.Payment;
import com.mustache.bbs5.domain.dto.PaymentRequestDto;
import com.mustache.bbs5.repository.DiseaseRepository;
import com.mustache.bbs5.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final DiseaseRepository diseaseRepository;
    private final HospitalRepository hospitalRepository;

    public PaymentService(DiseaseRepository diseaseRepository, HospitalRepository hospitalRepository) {
        this.diseaseRepository = diseaseRepository;
        this.hospitalRepository = hospitalRepository;
    }


    public void create(PaymentRequestDto dto) {
        Optional<Disease> optionalDisease = diseaseRepository.findById(dto.getDiseaseCode());
        Optional<Hospital> optionalHospital = hospitalRepository.findById(dto.getHospitalId());
        Payment payment = Payment.of(dto.getPatientName(), optionalHospital.get(), optionalDisease.get(), dto.getAmount());

    }
}
