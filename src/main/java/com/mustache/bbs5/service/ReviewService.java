package com.mustache.bbs5.service;

import com.mustache.bbs5.domain.Review;
import com.mustache.bbs5.domain.dto.ReviewCreateRequest;
import com.mustache.bbs5.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
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

        // review save
        Review savedReview = reviewRepository.save(Review.of(dto.getHospitalId(), dto.getTitle(), dto.getContent(), dto.getUserName()));

    }
}
