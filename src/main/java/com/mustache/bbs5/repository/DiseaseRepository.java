package com.mustache.bbs5.repository;

import com.mustache.bbs5.domain.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, String> {
}
