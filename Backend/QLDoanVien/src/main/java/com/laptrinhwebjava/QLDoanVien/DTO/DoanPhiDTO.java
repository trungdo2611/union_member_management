package com.laptrinhwebjava.QLDoanVien.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoanPhiDTO {
    private Long id;
    private String tenDoanVien;
    private String tenPhi;
    private Date ngayNop;
    private int soTien;
    private Long doanVien_id;
}
