package com.laptrinhwebjava.QLDoanVien.payload.response;

import com.laptrinhwebjava.QLDoanVien.DTO.DoanVienDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDoanVienResponse {
    private String message;
    private Long doanVienId;
}
