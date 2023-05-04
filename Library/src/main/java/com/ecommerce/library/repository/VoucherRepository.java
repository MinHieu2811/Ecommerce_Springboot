package com.ecommerce.library.repository;

import com.ecommerce.library.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    // Add any custom queries or methods here if needed
}