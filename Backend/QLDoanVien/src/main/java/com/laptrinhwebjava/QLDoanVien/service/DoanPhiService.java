package com.laptrinhwebjava.QLDoanVien.service;

import com.laptrinhwebjava.QLDoanVien.DTO.DoanPhiDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.DoanPhiDetailDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.DoanPhiSummaryDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.SoTayDoanVienDTO;
import com.laptrinhwebjava.QLDoanVien.model.SoTayDV;
import com.laptrinhwebjava.QLDoanVien.model.doanPhi;
import com.laptrinhwebjava.QLDoanVien.responsitory.DoanPhiRepo;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
@RequiredArgsConstructor
public class DoanPhiService {
    @Autowired
    private final DoanPhiRepo doanPhiRepo;

    public Page<DoanPhiDetailDTO> getAllDoanPhi(String search, int page, int size) {
        if (StringUtils.isEmpty(search)) {
            return doanPhiRepo.getListDoanPhi(PageRequest.of(page, size, Sort.by("id")));
        } else {
            return doanPhiRepo.getListDoanPhiByKeyword(
                    search, PageRequest.of(page, size, Sort.by("id")));
        }
    }

    public DoanPhiDetailDTO deltailDoanPhi(long id) {
        return doanPhiRepo.getDoanPhiDetail(id);
    }

    public void createDoanPhi(DoanPhiDTO doanPhiDTO) {
        if(doanPhiDTO != null) {
            doanPhiRepo.createDoanPhi(doanPhiDTO.getTenPhi(),doanPhiDTO.getNgayNop(),doanPhiDTO.getSoTien(),doanPhiDTO.getDoanVien_id());
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("doan phi is null");
        }

    }

    public void updateDoanPhi(DoanPhiDTO doanPhiDTO) {
        if(doanPhiDTO != null) {
            doanPhiRepo.updateDoanPhiByDoanVienId(doanPhiDTO.getTenPhi(),doanPhiDTO.getNgayNop(),doanPhiDTO.getSoTien(),doanPhiDTO.getId());
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("doan phi is null");
        }
    }

    public boolean deleteDoanPhi(long id) {
        if(id >= 1) {
            doanPhi doanphi = doanPhiRepo.getById(id);
            if(doanphi != null) {
                doanPhiRepo.delete(doanphi);
            }
            return true;
        }

        return false;
    }

    public List<DoanPhiSummaryDTO> listDoanPhiSummary() {
        return doanPhiRepo.listDoanPhiSummary();
    }

    public long countTotalDoanVienInDoanPhi() {
        return doanPhiRepo.countTotalDoanVienInDoanPhi();
    }

    public BigDecimal sumDoanPhi() {
        return doanPhiRepo.sumTotalMoneyInDoanPhi();
    }

}
