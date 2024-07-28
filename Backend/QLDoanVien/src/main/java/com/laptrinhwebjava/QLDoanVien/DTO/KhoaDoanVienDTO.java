package com.laptrinhwebjava.QLDoanVien.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhoaDoanVienDTO {
    private Long id;
    private String tenKhoa;
    private Long totalDoanVien;
}
