package com.laptrinhwebjava.QLDoanVien.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoanPhiSummaryDTO {
    private Long id;
    private String tenDoanVien;
    private BigDecimal tongSoTien;
    private String tenChiDoan;
    private String tenKhoa;
}
