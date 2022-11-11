package com.mustache.bbs5.repository;

import com.mustache.bbs5.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByHospitalId(Integer hospitalId);
}
