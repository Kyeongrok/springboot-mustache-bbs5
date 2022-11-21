package com.mustache.bbs5.controller;

import com.mustache.bbs5.domain.Hospital;
import com.mustache.bbs5.domain.Review;
import com.mustache.bbs5.domain.dto.HospitalSearchRequestDto;
import com.mustache.bbs5.repository.HospitalRepository;
import com.mustache.bbs5.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hospitals")
@Slf4j
public class HospitalController {
    private final HospitalRepository hospitalRepository;
    private final ReviewRepository reviewRepository;

    public HospitalController(HospitalRepository hospitalRepository, ReviewRepository reviewRepository) {
        this.hospitalRepository = hospitalRepository;
        this.reviewRepository = reviewRepository;
    }


    @GetMapping(value = {""})
    public String search2(@RequestParam(required = false) String keyword,
                          Model model,
                          @PageableDefault(size = 20) Pageable pageable) {
        Page<Hospital> hospitals;
        if (keyword != null) {
            hospitals = hospitalRepository.findByRoadNameAddressContaining(keyword, pageable);
        }else {
            hospitals = hospitalRepository.findAll(pageable);
        }
        log.info("size:{} keyword:", hospitals.getSize(), keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospital/search";
    }

    @GetMapping("/all")
    public String list(Model model, Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        log.info("size:{}", hospitals.getSize());
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospital/listAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model, Pageable pageable) {
        Optional<Hospital> hospital = hospitalRepository.findById(id);

        Page<Review> reviews = reviewRepository.findByHospitalId(id, pageable);
        log.info("review cnt:{} {}", reviews.getSize(), reviews);
        model.addAttribute("hospital", hospital.get());
        model.addAttribute("reviews", reviews);
        return "hospital/show";
    }
}
