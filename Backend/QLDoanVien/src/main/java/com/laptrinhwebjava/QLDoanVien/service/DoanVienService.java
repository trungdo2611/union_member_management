package com.laptrinhwebjava.QLDoanVien.service;

import com.laptrinhwebjava.QLDoanVien.DTO.*;
import com.laptrinhwebjava.QLDoanVien.model.ChiDoan;
import com.laptrinhwebjava.QLDoanVien.model.DoanVien;
import com.laptrinhwebjava.QLDoanVien.model.Khoa;
import com.laptrinhwebjava.QLDoanVien.responsitory.ChiDoanRepo;
import com.laptrinhwebjava.QLDoanVien.responsitory.DoanVienRepo;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.Servlet;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.laptrinhwebjava.QLDoanVien.constant.Constant.PHOTO_DIRECTORY;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
@RequiredArgsConstructor
public class DoanVienService {
    @Autowired
    private final DoanVienRepo doanVienRepo;

    @Autowired
    private final ChiDoanRepo chiDoanRepo;

    private boolean checkBiThuInChiDoan(Long chiDoanId, Long chucVu_id) {
        if(chucVu_id == 1) {
            int countBiThu = doanVienRepo.countBiThuInChiDoan(chiDoanId, chucVu_id);
            return countBiThu > 0;
        }
        return false;
    }
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomPassword(int length) {
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }

        return password.toString();
    }

    private String generateMaDoanVien(ChiDoan chiDoan, Long lastDoanVienId) {
        // Lấy tên mã chi doan và thêm tiền tố 'CĐ'
        String tenMaDV = "ĐV" + chiDoan.getMaChiDoan()+"00";

        Long nextDoanVienId = lastDoanVienId + 1;

        // Tạo mã chi doan
        return tenMaDV + nextDoanVienId;

    }

    public Page<DoanVienDetailDTO> getAllPageDetail(String keyWord, int page, int size) {
        if (StringUtils.isEmpty(keyWord)) {
            return doanVienRepo.ListPageDetailDV(PageRequest.of(page, size));
        } else {
            return doanVienRepo.findPageDoanVienByKeyword(keyWord, PageRequest.of(page, size));
        }
    }

    public List<DoanVienDetailDTO> getAllDetail(String keyWord) {
        if (StringUtils.isEmpty(keyWord)) {
            return doanVienRepo.ListDetailDV();
        } else {
            return doanVienRepo.findDoanVienByKeyword(keyWord);
        }
    }

    public List<DoanVienSelectDTO> getDoanVienSelectByChiDoan(Long chiDoan_id) {
        return doanVienRepo.findDoanVienByIdChiDoan(chiDoan_id);
    }

    public List<DoanVienSelectDTO> getDoanVienSelect() {
        return doanVienRepo.findDoanVien();
    }

    public DoanVienDetailDTO deltailDoanVien(long id) {
        return doanVienRepo.findDoanVienById(id);
    }

    public Long createDV(DoanVienDTO doanVienDTO) {
        if(doanVienDTO != null) {
            Long chiDoanId = doanVienDTO.getChiDoan_id();
            Long chucVu_id = doanVienDTO.getChucVu_id();
            String email = doanVienDTO.getEmail();
            if (doanVienRepo.existsByEmail(email)) {
                return null;
            }
            if(checkBiThuInChiDoan(chiDoanId,chucVu_id)) {
                return  null;
            }

                ChiDoan chiDoan = chiDoanRepo.findById(chiDoanId).get();
                Optional<Long> optionalLastDoanVienId = doanVienRepo.findTop();
                Long lastDoanVienId = optionalLastDoanVienId.isPresent() ? optionalLastDoanVienId.get() : 0L;
                String maDoanVien = generateMaDoanVien(chiDoan, lastDoanVienId);
                String randomPassword = generateRandomPassword(7);
                doanVienRepo.createDoanVien(maDoanVien,doanVienDTO.getTenDoanVien(),doanVienDTO.getNgaySinh(),doanVienDTO.getGioiTinh(),doanVienDTO.getDanToc(),email,doanVienDTO.getDienThoai(),doanVienDTO.getNgayVaoDoan(),randomPassword,doanVienDTO.getHinhAnh(),doanVienDTO.getChiDoan_id(),doanVienDTO.getChucVu_id(),Long.parseLong("2"));
                Long newDoanVienId = doanVienRepo.findDoanVienIdByEmail(doanVienDTO.getEmail());
                return newDoanVienId;
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DoanVienDTO is null");
            return null;
        }
    }

    public String uploadPhoto(long id, MultipartFile file) {
        log.info("saving picture for Id doan vien : {}" , id);
       DoanVien doanVien = doanVienRepo.getById(id);
       String photoUrl = photoFunction.apply(id, file);
       doanVien.setHinhAnh(photoUrl);
        doanVienRepo.save(doanVien);
        return photoUrl;
    }

    private final Function<String , String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
            .map(name -> name.substring(filename.lastIndexOf(".") + 1)).orElse(".png");

    private final BiFunction<Long, MultipartFile, String> photoFunction = (id, image) -> {
        String fileName ="hinh_anh_"+generateRandomPassword(7)+id + "."+fileExtension.apply(image.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if(!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(fileName),REPLACE_EXISTING);
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/doanvien/image/"+fileName).toUriString();
        } catch (Exception ex) {
            throw new RuntimeException("Không lưu được ảnh");
        }
    };



    private boolean isBiThu(Long id) {
            int countBiThu = doanVienRepo.isBiThu(id);
            return countBiThu > 0;
    }

    public void updateDV(DoanVienDTO doanVienDTO) {
        if(doanVienDTO != null) {
            Long chiDoanId = doanVienDTO.getChiDoan_id();
            Long chucVu_id = doanVienDTO.getChucVu_id();
            Long id = doanVienDTO.getId();
            if(isBiThu(id)) {
                doanVienRepo.updateDoanVien(doanVienDTO.getMaDoanVien(),doanVienDTO.getTenDoanVien(),doanVienDTO.getNgaySinh(),doanVienDTO.getGioiTinh(),doanVienDTO.getDanToc(),doanVienDTO.getDienThoai(),doanVienDTO.getNgayVaoDoan(),doanVienDTO.getPassword(),doanVienDTO.getChiDoan_id(),doanVienDTO.getChucVu_id(),doanVienDTO.getPhanQuyen_id(),doanVienDTO.getId());
            }
            else {
               if(!checkBiThuInChiDoan(chiDoanId,chucVu_id)) {
                   {
                       doanVienRepo.updateDoanVien(doanVienDTO.getMaDoanVien(),doanVienDTO.getTenDoanVien(),doanVienDTO.getNgaySinh(),doanVienDTO.getGioiTinh(),doanVienDTO.getDanToc(),doanVienDTO.getDienThoai(),doanVienDTO.getNgayVaoDoan(),doanVienDTO.getPassword(),doanVienDTO.getChiDoan_id(),doanVienDTO.getChucVu_id(),doanVienDTO.getPhanQuyen_id(),doanVienDTO.getId());
                   }
               }
            }

        }else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("doanvien dto is null");
        }
    }

    public void updatePassword(DoanVienDTO doanVienDTO) {
        if(doanVienDTO != null) {
            doanVienRepo.updatePassword(doanVienDTO.getPassword(),doanVienDTO.getId());
        }else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("doanvien dto is null");
        }
    }

    public boolean deleteDoanVien(long id) {
        if(id >= 1) {
            DoanVien doanVien = doanVienRepo.getById(id);
            if(doanVien != null) {
                doanVienRepo.delete(doanVien);
            }
            return true;
        }

        return false;
    }

    public List<KhoaDoanVienDTO> countDoanVienByKhoa() {
        return doanVienRepo.countDoanVienByKhoa();
    }

    public long countTotalDoanVien() {
        return doanVienRepo.countTotalDoanVien();
    }
}
