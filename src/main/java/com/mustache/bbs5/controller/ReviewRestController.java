package com.mustache.bbs5.controller;

import com.mustache.bbs5.domain.Review;
import com.mustache.bbs5.domain.dto.ReviewCreateRequest;
import com.mustache.bbs5.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewRestController {

    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(reviewService.get(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ReviewCreateRequest dto) {
        reviewService.create(dto);
        return ResponseEntity.ok().body(null);
    }
}
