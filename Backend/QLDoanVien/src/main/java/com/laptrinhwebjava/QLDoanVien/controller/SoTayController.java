package com.laptrinhwebjava.QLDoanVien.controller;

import com.laptrinhwebjava.QLDoanVien.DTO.DoanVienDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.SoTayDetailDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.SoTayDoanVienDTO;
import com.laptrinhwebjava.QLDoanVien.model.Khoa;
import com.laptrinhwebjava.QLDoanVien.model.SoTayDV;
import com.laptrinhwebjava.QLDoanVien.responsitory.SoTayRepo;
import com.laptrinhwebjava.QLDoanVien.service.SoTayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sotay")
@RequiredArgsConstructor
public class SoTayController {
    @Autowired
    private final SoTayService soTayService;

    @GetMapping("/listSotay")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<SoTayDetailDTO>> listSotay(@RequestParam(value = "idDV") Long idDV) {
        return ResponseEntity.ok().body(soTayService.getAllSoTay(idDV));
    }

    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    //@PreAuthorize("hasAnyRole('admin', 'user')")
    public ResponseEntity<Boolean> deleteSoTay(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok().body(soTayService.deleteSoTay(id));
    }

    @PutMapping("/edit")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    //@PreAuthorize("hasAnyRole('admin', 'user')")
    public ResponseEntity<?> updateST(@RequestBody SoTayDoanVienDTO soTayDoanVienDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }
        soTayService.updateSoTay(soTayDoanVienDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cập nhật nội dung sổ tay thành công");
    }

    @PostMapping("/create")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createST(@RequestBody SoTayDoanVienDTO soTayDoanVienDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }
        soTayService.createSoTay(soTayDoanVienDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(" tạo nội dung sổ tay thành công");
    }
}
