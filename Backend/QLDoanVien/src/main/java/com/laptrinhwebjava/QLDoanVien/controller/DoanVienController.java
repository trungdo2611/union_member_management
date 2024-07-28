package com.laptrinhwebjava.QLDoanVien.controller;

import com.laptrinhwebjava.QLDoanVien.DTO.*;
import com.laptrinhwebjava.QLDoanVien.payload.response.CreateDoanVienResponse;
import com.laptrinhwebjava.QLDoanVien.responsitory.DoanVienRepo;
import com.laptrinhwebjava.QLDoanVien.service.DoanVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.laptrinhwebjava.QLDoanVien.constant.Constant.PHOTO_DIRECTORY;
import static org.springframework.util.MimeTypeUtils.IMAGE_JPEG_VALUE;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/doanvien")
@RequiredArgsConstructor
public class DoanVienController {
    private boolean checkBiThuInChiDoan(Long chiDoanId, Long chucVu_id) {
        if(chucVu_id == 1) {
            int countBiThu = doanVienRepo.countBiThuInChiDoan(chiDoanId, chucVu_id);
            return countBiThu > 0;
        }
        return false;
    }

    private boolean isBiThu(Long id) {
        int countBiThu = doanVienRepo.isBiThu(id);
        return countBiThu > 0;
    }

    @Autowired
    private final DoanVienService doanVienService;

    @Autowired
    private  final DoanVienRepo doanVienRepo;
    @GetMapping("/listPagedoanvien")
    public ResponseEntity<Page<DoanVienDetailDTO>> listPageDoanVien(
            @RequestParam String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "6") int size) {
        return ResponseEntity.ok().body(doanVienService.getAllPageDetail(search,page, size));
    }
    @GetMapping("/listdoanvien")
    public ResponseEntity<List<DoanVienDetailDTO>> listChiDoan(@RequestParam String search) {
        return ResponseEntity.ok().body(doanVienService.getAllDetail(search));
    }

    @GetMapping("/detailDoanVien/{id}")
    public ResponseEntity<DoanVienDetailDTO> detailDoanVien(@PathVariable(value = "id") long id) {
        DoanVienDetailDTO doanVienDetailDTO = doanVienService.deltailDoanVien(id);
        if ( doanVienDetailDTO== null) {
            return new ResponseEntity<DoanVienDetailDTO>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<DoanVienDetailDTO>(doanVienDetailDTO, HttpStatus.OK);
    }

    @GetMapping("/getDoanVienSelectByChiDoan")
    public ResponseEntity<List<DoanVienSelectDTO>> getDoanVienSelectByChiDoan(
            @RequestParam(value = "chiDoan_id") Long chiDoan_id
    ) {
        return ResponseEntity.ok().body(doanVienService.getDoanVienSelectByChiDoan(chiDoan_id));
    }

    @GetMapping("/getDoanVienSelect")
    public ResponseEntity<List<DoanVienSelectDTO>> getDoanVienSelect() {
        return ResponseEntity.ok().body(doanVienService.getDoanVienSelect());
    }


    @PostMapping("/create")
    public ResponseEntity<?> createDoanVien(
            @RequestBody DoanVienDTO doanVienDTO, BindingResult
            bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }
        Long chiDoanId = doanVienDTO.getChiDoan_id();
        Long chucVu_id = doanVienDTO.getChucVu_id();
        String email = doanVienDTO.getEmail();
        if (doanVienRepo.existsByEmail(email)) {
            // Nếu email đã tồn tại, trả về ResponseEntity lỗi
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email đã tồn tại");
        }
        if(checkBiThuInChiDoan(chiDoanId,chucVu_id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Chi đoàn đã có bí thư vui lòng chọn chức vụ khác");
        }
        CreateDoanVienResponse response = new CreateDoanVienResponse("Tạo đoàn viên thành công", doanVienService.createDV(doanVienDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> updateDV(@RequestBody DoanVienDTO doanVienDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }

        Long chiDoanId = doanVienDTO.getChiDoan_id();
        Long chucVu_id = doanVienDTO.getChucVu_id();
        Long id = doanVienDTO.getId();

        
        if(isBiThu(id)) {
            doanVienService.updateDV(doanVienDTO);
        }
        else {
            if(!checkBiThuInChiDoan(chiDoanId,chucVu_id)) {
                {
                    doanVienService.updateDV(doanVienDTO);
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Chi đoàn đã có bí thư vui lòng chọn chức vụ khác");
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Cập nhật đoàn viên thành công");
    }

    @PutMapping("/changepass")
    public ResponseEntity<?> updatePassword(@RequestBody DoanVienDTO doanVienDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }
        doanVienService.updatePassword(doanVienDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Thay đổi password đoàn viên thành công");
    }

    @PutMapping("/photo")
    public ResponseEntity<String> uploadPhoto(
            @RequestParam("id") long id,
            @RequestParam("file")MultipartFile file) {
        return ResponseEntity.ok().body(doanVienService.uploadPhoto(id,file));
    }

    @GetMapping(value = "/image/{filename}", produces = {IMAGE_PNG_VALUE,IMAGE_JPEG_VALUE})
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }

    @GetMapping("/thongkeDoanVien")
    public ResponseEntity<List<KhoaDoanVienDTO>> thongKeDoanVien() {
        return ResponseEntity.ok().body(doanVienService.countDoanVienByKhoa());
    }

    @GetMapping("/countDoanVien")
    public ResponseEntity<Long> countTotalDoanVien() {
        long totalDoanVien = doanVienService.countTotalDoanVien();
        return ResponseEntity.ok(totalDoanVien);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCD(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok().body(doanVienService.deleteDoanVien(id));
    }
}
