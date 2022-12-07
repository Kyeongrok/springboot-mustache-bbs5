package com.mustache.bbs5.service;

import com.mustache.bbs5.domain.Hospital;
import com.mustache.bbs5.domain.Review;
import com.mustache.bbs5.domain.dto.ReviewCreateRequest;
import com.mustache.bbs5.repository.HospitalRepository;
import com.mustache.bbs5.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;

    public ReviewService(ReviewRepository reviewRepository, HospitalRepository hospitalRepository) {
        this.reviewRepository = reviewRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public Review get(Long id) {
        Optional<Review> reviews = reviewRepository.findById(id);
        return reviews.get();
    }

    public Page<Review> getReviews(Integer hospitalId, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findByHospitalId(hospitalId, pageable);
        return reviews;
    }

    public void create(ReviewCreateRequest dto) {
        // hospitalId를 받아서 Hospital을 select함
        Optional<Hospital> optionalHospital = hospitalRepository.findById(dto.getHospitalId());
        Review savedReview = reviewRepository.save(Review.of(
                optionalHospital.orElseThrow(()-> new IllegalArgumentException("해당 hospitalId에 해당하는 병원이 없습니다.")),
                dto.getTitle(), dto.getContent(), dto.getUserName()));
    }
}
