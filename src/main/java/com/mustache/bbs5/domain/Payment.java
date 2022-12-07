package com.mustache.bbs5.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 병원 id
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    // 질병코드
    @ManyToOne
    @JoinColumn(name = "disease_code")
    private Disease disease;

    private Double amount;


    public static Payment of(String patientName, Hospital hospital, Disease disease, Double amount) {
        return new Payment();
    }
}
