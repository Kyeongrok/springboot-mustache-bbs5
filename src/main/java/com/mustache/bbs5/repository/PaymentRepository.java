package com.mustache.bbs5.repository;

import com.mustache.bbs5.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
