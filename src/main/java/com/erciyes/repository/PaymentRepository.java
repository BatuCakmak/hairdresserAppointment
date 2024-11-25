package com.erciyes.repository;

import com.erciyes.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

}
