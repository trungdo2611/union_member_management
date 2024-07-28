package com.laptrinhwebjava.QLDoanVien.controller;

import com.laptrinhwebjava.QLDoanVien.DTO.ChiDoanDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.KhoaDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.KhoaDetailDTO;
import com.laptrinhwebjava.QLDoanVien.model.Khoa;
import com.laptrinhwebjava.QLDoanVien.service.KhoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/khoa")
@RequiredArgsConstructor
public class KhoaController {
    @Autowired
    private final KhoaService khoaService;
    @GetMapping("/listKhoa")
    public ResponseEntity<Page<KhoaDetailDTO>> listKhoa(
            @RequestParam(value = "search") String search,
            @RequestParam(value = "page" , defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "6") int size
    ) {
        return ResponseEntity.ok().body(khoaService.getAllKhoa(search,page,size));
    }

    @GetMapping("/getKhoaSelect")
    public ResponseEntity<List<KhoaDTO>> getKhoaSelect() {
        return ResponseEntity.ok().body(khoaService.getKhoaSelect());
    }

    @GetMapping("/detailKhoa/{id}")
    public ResponseEntity<KhoaDetailDTO> detailKhoa(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok().body(khoaService.deltailKhoa(id));
    }
    @PostMapping("/create")
    public ResponseEntity<Khoa> createKhoa(@RequestBody Khoa khoa) {
        return ResponseEntity.ok().body(khoaService.createKhoa(khoa));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> updateKhoa(@RequestBody Khoa khoa, BindingResult
            bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }
        khoaService.udpateKhoa(khoa);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cập nhật khoa thành công");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteKhoa(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok().body(khoaService.deleteKhoa(id));
    }
}
