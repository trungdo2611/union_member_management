package com.laptrinhwebjava.QLDoanVien.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoTayDoanVienDTO {
    Long id;
    String noiDung;
    Long doanVien_id;
}
