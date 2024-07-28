package com.laptrinhwebjava.QLDoanVien.service;

import com.laptrinhwebjava.QLDoanVien.DTO.KhoaDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.KhoaDetailDTO;
import com.laptrinhwebjava.QLDoanVien.model.Khoa;
import com.laptrinhwebjava.QLDoanVien.responsitory.KhoaRepo;
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

import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
@RequiredArgsConstructor
public class KhoaService {
    @Autowired
    private final KhoaRepo khoaRepo;


    public Page<KhoaDetailDTO> getAllKhoa(String search, int page, int size) {
        if (StringUtils.isEmpty(search)) {
            return khoaRepo.getAllKhoa(PageRequest.of(page, size, Sort.by("id")));
        } else {
            return khoaRepo.searchKhoa(
                    search, PageRequest.of(page, size, Sort.by("id")));
        }
    }

    public KhoaDetailDTO deltailKhoa(long id) {
        return khoaRepo.findKhoaById(id);
    }

    public List<KhoaDTO> getKhoaSelect() {
        return khoaRepo.getKhoaSelect();
    }
    public Khoa createKhoa(Khoa khoa) {
        if(khoa !=null) {
            return khoaRepo.save(khoa);
        }
        return null;
    }

    public void udpateKhoa(Khoa khoa) {
        if(khoa !=null) {
            khoaRepo.updateKhoa(khoa.getTenKhoa(),khoa.getMaKhoa(),khoa.getEmail(),khoa.getDienThoai(),khoa.getId());
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khoa is null");
        }
    }

    public boolean deleteKhoa(long id) {
        if(id >= 1) {
            Khoa khoa = khoaRepo.getById(id);
            if(khoa != null) {
                khoaRepo.delete(khoa);
            }
            return true;
        }

        return false;
    }
}
