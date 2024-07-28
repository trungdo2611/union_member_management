package com.laptrinhwebjava.QLDoanVien.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiDoanDTO {
    Long id;
    String maChiDoan;
    String tenChiDoan;
    String diaChi;
    String dienThoai;
    Long khoa_id;
}
