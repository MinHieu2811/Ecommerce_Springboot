package com.ecommerce.library.service;

import com.ecommerce.library.model.Voucher;

import java.util.List;

public interface VoucherService {
    Voucher addVoucher(Voucher voucher);
    void deleteVoucher(Long voucherId);
    List<Voucher> findAll(); // Add this method to your VoucherService interface

    // Add any other voucher-related methods you may need
}
