package com.mustache.bbs5.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentRequestDto {
    private String patientName;
    private Integer hospitalId;
    private String diseaseCode;
    private Double amount;
}
