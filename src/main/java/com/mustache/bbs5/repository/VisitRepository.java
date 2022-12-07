package com.mustache.bbs5.repository;

import com.mustache.bbs5.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
