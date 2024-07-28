package com.laptrinhwebjava.QLDoanVien.service;

import com.laptrinhwebjava.QLDoanVien.model.DoanVien;
import com.laptrinhwebjava.QLDoanVien.responsitory.DoanVienRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
@RequiredArgsConstructor
public class SecurityService {
    @Autowired
    private final DoanVienRepo doanVienRepo;

    public DoanVien findByMaDoanVien(String email) {
        return doanVienRepo.findByEmail(email);
    }

    public boolean existsByMaDoanVien(String maDoanVien) {
        return doanVienRepo.existsByMaDoanVien(maDoanVien);
    }

    public boolean existsByEmail(String email) {
        return doanVienRepo.existsByEmail(email);
    }

}
