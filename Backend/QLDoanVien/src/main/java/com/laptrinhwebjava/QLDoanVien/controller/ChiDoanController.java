package com.laptrinhwebjava.QLDoanVien.controller;

import com.laptrinhwebjava.QLDoanVien.DTO.ChiDoanDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.ChiDoanDetailDTO;
import com.laptrinhwebjava.QLDoanVien.model.ChiDoan;
import com.laptrinhwebjava.QLDoanVien.model.Khoa;
import com.laptrinhwebjava.QLDoanVien.service.ChiDoanService;
import com.laptrinhwebjava.QLDoanVien.service.KhoaService;
import jakarta.websocket.server.PathParam;
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
@RequestMapping("/chidoan")
@RequiredArgsConstructor
public class ChiDoanController {

    @Autowired
    private final ChiDoanService chiDoanService;

    @GetMapping("/listChiDoan")
    public ResponseEntity<Page<ChiDoanDetailDTO>> listChiDoan(
            @RequestParam(value = "search") String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "6") int size
    ) {
        return ResponseEntity.ok().body(chiDoanService.getAllChiDoan(search,page,size));
    }

        @GetMapping("/getChiDoanSelectByKhoa")
    public ResponseEntity<List<ChiDoanDetailDTO>> getChiDoanSelectByKhoa(
            @RequestParam(value = "khoa_id") Long khoa_id
    ) {
        return ResponseEntity.ok().body(chiDoanService.getChiDoanSelectByKhoa(khoa_id));
    }

    @GetMapping("/getChiDoanSelect")
    public ResponseEntity<List<ChiDoanDetailDTO>> getChiDoanSelect() {
        return ResponseEntity.ok().body(chiDoanService.getChiDoanSelect());
    }
    @GetMapping("/detailChiDoan/{id}")
    public ResponseEntity<ChiDoanDetailDTO> detailKhoa(@PathVariable(value = "id") long id) {
        ChiDoanDetailDTO chiDoanDetailDTO = chiDoanService.deltailChiDoan(id);
        if ( chiDoanDetailDTO== null) {
            return new ResponseEntity<ChiDoanDetailDTO>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<ChiDoanDetailDTO>(chiDoanDetailDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createChiDoan(
            @RequestBody ChiDoanDTO chiDoan, BindingResult
            bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }
        chiDoanService.createCD(chiDoan);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tạo chi đoàn thành công");
    }

    @PutMapping("/edit")
    public ResponseEntity<?> updateCD(
            @RequestBody ChiDoanDTO chiDoan, BindingResult
            bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }
        chiDoanService.updateCD(chiDoan);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cập nhật chi đoàn thành công");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCD(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok().body(chiDoanService.deleteChiDoan(id));
    }
}
