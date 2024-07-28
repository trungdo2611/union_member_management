package com.laptrinhwebjava.QLDoanVien.service;

import com.laptrinhwebjava.QLDoanVien.DTO.ChiDoanDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.ChiDoanDetailDTO;
import com.laptrinhwebjava.QLDoanVien.model.ChiDoan;
import com.laptrinhwebjava.QLDoanVien.model.Khoa;
import com.laptrinhwebjava.QLDoanVien.responsitory.ChiDoanRepo;
import com.laptrinhwebjava.QLDoanVien.responsitory.KhoaRepo;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
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

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
@RequiredArgsConstructor
public class ChiDoanService {
    @Autowired
    private final ChiDoanRepo chiDoanRepo;

    @Autowired
    private final KhoaRepo khoaRepo;

    private String generateMaChiDoan(Khoa khoa,Long lastChiDoanId) {
        // Lấy tên mã khoa và thêm tiền tố 'CĐ'
        String tenMaKhoa = "CĐ" + khoa.getMaKhoa();
        Long nextChiDoanId = lastChiDoanId + 1;
        // Tạo mã chi doan
        return tenMaKhoa + nextChiDoanId;
    }

    public Page<ChiDoanDetailDTO> getAllChiDoan(String search,int page, int size) {
        if (StringUtils.isEmpty(search)) {
            return chiDoanRepo.listChiDoan(PageRequest.of(page, size));
        } else {
            return chiDoanRepo.searchChiDoan(
                    search, PageRequest.of(page, size));
        }
    }

    public List<ChiDoanDetailDTO> getChiDoanSelectByKhoa(Long khoa_id) {
        return chiDoanRepo.findChiDoanByIdKhoa(khoa_id);
    }

    public List<ChiDoanDetailDTO> getChiDoanSelect() {
        return chiDoanRepo.findChiDoan();
    }
    public ChiDoanDetailDTO deltailChiDoan(long id) {
        return chiDoanRepo.findChiDoanById(id);
    }


    public void createCD(ChiDoanDTO chiDoanDTO) {
        if(chiDoanDTO != null) {
            long khoaId = chiDoanDTO.getKhoa_id();
            Khoa khoa = khoaRepo.findById(khoaId).get();
            Optional<Long> optionalLastChiDoanId = chiDoanRepo.findTop();
            Long lastChiDoanId = optionalLastChiDoanId.isPresent() ? optionalLastChiDoanId.get() : 0L;

            String maChiDoan = generateMaChiDoan(khoa, lastChiDoanId);
            chiDoanRepo.createChiDoan(maChiDoan,chiDoanDTO.getTenChiDoan(),chiDoanDTO.getDiaChi(),chiDoanDTO.getDienThoai(),chiDoanDTO.getKhoa_id());
        } else {
             ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ChiDoanDTO is null");
        }

    }

    public void updateCD(ChiDoanDTO chiDoanDTO) {
        if(chiDoanDTO != null) {
            chiDoanRepo.updateChiDoan(chiDoanDTO.getMaChiDoan(),chiDoanDTO.getTenChiDoan(),chiDoanDTO.getDiaChi(),chiDoanDTO.getDienThoai(),chiDoanDTO.getKhoa_id(),chiDoanDTO.getId());
        }else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ChiDoanDTO is null");
        }
    }

    public boolean deleteChiDoan(long id) {
        if(id >= 1) {
            ChiDoan chiDoan = chiDoanRepo.getById(id);
            if(chiDoan != null) {
                chiDoanRepo.delete(chiDoan);
            }
            return true;
        }

        return false;
    }
}
