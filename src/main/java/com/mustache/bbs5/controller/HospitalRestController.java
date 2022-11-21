package com.mustache.bbs5.controller;

import com.mustache.bbs5.domain.Review;
import com.mustache.bbs5.domain.dto.HospitalResponse;
import com.mustache.bbs5.service.HospitalService;
import com.mustache.bbs5.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {

    private final HospitalService hospitalService;
    private final ReviewService reviewService;

    public HospitalRestController(HospitalService hospitalService, ReviewService reviewService) {
        this.hospitalService = hospitalService;
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id) { // ResponseEntity도 DTO타입
        HospitalResponse hospitalResponse = hospitalService.getHospital(id); // DTO
        return ResponseEntity.ok().body(hospitalResponse); // Return은 DTO로
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<Page<Review>> getReviews(@PathVariable Integer id) {
        Pageable pageable = Pageable.ofSize(20);
        return ResponseEntity.ok().body(reviewService.getReviews(id, pageable));
    }
}
