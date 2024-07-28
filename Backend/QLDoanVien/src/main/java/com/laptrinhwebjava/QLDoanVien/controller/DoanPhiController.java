package com.laptrinhwebjava.QLDoanVien.controller;

import com.laptrinhwebjava.QLDoanVien.DTO.*;
import com.laptrinhwebjava.QLDoanVien.model.ChiDoan;
import com.laptrinhwebjava.QLDoanVien.service.ChiDoanService;
import com.laptrinhwebjava.QLDoanVien.service.DoanPhiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/doanphi")
@RequiredArgsConstructor
public class DoanPhiController {
    @Autowired
    private final DoanPhiService doanPhiService;

    @GetMapping("/listDoanPhi")
    public ResponseEntity<Page<DoanPhiDetailDTO>> listDoanPhi(
            @RequestParam(value = "search") String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "6") int size
    ) {
        return ResponseEntity.ok().body(doanPhiService.getAllDoanPhi(search, page, size));
    }

    @GetMapping("/detailDoanPhi/{id}")
    public ResponseEntity<DoanPhiDetailDTO> detailDoanPhi(@PathVariable(value = "id") long id) {
        DoanPhiDetailDTO doanPhiDetailDTO = doanPhiService.deltailDoanPhi(id);
        if ( doanPhiDetailDTO== null) {
            return new ResponseEntity<DoanPhiDetailDTO>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<DoanPhiDetailDTO>(doanPhiDetailDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDoanPhi(
            @RequestBody DoanPhiDTO doanPhi, BindingResult
            bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }
        doanPhiService.createDoanPhi(doanPhi);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tạo đoàn phí thành công");
    }

    @PutMapping("/edit")
    public ResponseEntity<?> updateDoanPhi(
            @RequestBody DoanPhiDTO doanphi, BindingResult
                    bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }
        doanPhiService.updateDoanPhi(doanphi);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cập nhật đoàn phí thành công");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteDoanPhi(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok().body(doanPhiService.deleteDoanPhi(id));
    }

        @GetMapping("/thongkeDoanPhi")
    public ResponseEntity<List<DoanPhiSummaryDTO>> thongKeDoanPhi() {
        return ResponseEntity.ok().body(doanPhiService.listDoanPhiSummary());
    }

    @GetMapping("/countDVInDoanPhi")
    public ResponseEntity<Long> countTotalDoanVienfromDoanPhi() {
        long totalDoanVien = doanPhiService.countTotalDoanVienInDoanPhi();
        return ResponseEntity.ok(totalDoanVien);
    }

    @GetMapping("/sumDoanPhi")
    public ResponseEntity<BigDecimal> TotalDoanPhi() {
        BigDecimal totalDoanVien = doanPhiService.sumDoanPhi();
        return ResponseEntity.ok(totalDoanVien);
    }
}
