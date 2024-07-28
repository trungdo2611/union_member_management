package com.laptrinhwebjava.QLDoanVien.service;

import com.laptrinhwebjava.QLDoanVien.DTO.ChiDoanDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.SoTayDetailDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.SoTayDoanVienDTO;
import com.laptrinhwebjava.QLDoanVien.model.ChiDoan;
import com.laptrinhwebjava.QLDoanVien.model.Khoa;
import com.laptrinhwebjava.QLDoanVien.model.SoTayDV;
import com.laptrinhwebjava.QLDoanVien.responsitory.ChiDoanRepo;
import com.laptrinhwebjava.QLDoanVien.responsitory.DoanVienRepo;
import com.laptrinhwebjava.QLDoanVien.responsitory.KhoaRepo;
import com.laptrinhwebjava.QLDoanVien.responsitory.SoTayRepo;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
@RequiredArgsConstructor
public class SoTayService {
    @Autowired
    private final SoTayRepo soTayRepo;

    public List<SoTayDetailDTO> getAllSoTay(Long idDV) {
      return soTayRepo.getSoTayDoanVien(idDV);
    }


    public void createSoTay(SoTayDoanVienDTO soTayDoanVienDTO) {
        if(soTayDoanVienDTO != null) {
            soTayRepo.createSoTayDoanVien(soTayDoanVienDTO.getNoiDung(),soTayDoanVienDTO.getDoanVien_id());
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("so tay is null");
        }

    }

    public void updateSoTay(SoTayDoanVienDTO soTayDoanVienDTO) {
        if(soTayDoanVienDTO != null) {
            soTayRepo.updateSoTayDoanVien(soTayDoanVienDTO.getNoiDung(),soTayDoanVienDTO.getId());
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("so tay is null");
        }

    }

    public boolean deleteSoTay(long id) {
        if(id >= 1) {
            SoTayDV soTayDV = soTayRepo.getById(id);
            if(soTayDV != null) {
                soTayRepo.delete(soTayDV);
            }
            return true;
        }

        return false;
    }
}
