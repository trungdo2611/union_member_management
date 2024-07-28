package com.laptrinhwebjava.QLDoanVien.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.laptrinhwebjava.QLDoanVien.model.DoanVien;
import com.laptrinhwebjava.QLDoanVien.model.PhanQuyen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class customUser implements UserDetails {
    private Long id;
    private String maDoanVien;
    private String tenDoanVien;
    private Date ngaySinh;
    private Boolean gioiTinh;
    private String danToc;
    private String email;
    private String dienThoai;
    private Date ngayVaoDoan;
    private String password;
    private String hinhAnh;
    private Long chucVu_id;
    private Long phanQuyen_id;
    private String tenChiDoan;
    private String tenChucVu;
    private String tenPhanQuyen;
    private String tenKhoa;
    private Collection<GrantedAuthority> authorities;


    // tu thong tin doan vien chuyen sang customUser
    public static customUser mapDoanVienToCustomUser (DoanVien doanVien) {
//        List<GrantedAuthority> authorities = doanVien.
//         .map(phanQuyens -> new SimpleGrantedAuthority(phanQuyens.getTenQuyen().name())
//         .collect(Collertor.tolist());


        List<GrantedAuthority> authorities = new ArrayList<>();
        PhanQuyen phanQuyen = doanVien.getPhanQuyen(); // Lấy phân quyền của đoàn viên trực tiếp

        // Kiểm tra nếu đoàn viên có phân quyền
        if (phanQuyen != null) {
            // Tạo một SimpleGrantedAuthority từ tên quyền của phân quyền và thêm vào danh sách
            authorities.add(new SimpleGrantedAuthority("ROLE_"+phanQuyen.getTenQuyen()));
        }


        return new customUser(
                doanVien.getId(),
                doanVien.getMaDoanVien(),
                doanVien.getTenDoanVien(),
                doanVien.getNgaySinh(),
                doanVien.isGioiTinh(),
                doanVien.getDanToc(),
                doanVien.getEmail(),
                doanVien.getDienThoai(),
                doanVien.getNgayVaoDoan(),
                doanVien.getPassword(),
                doanVien.getHinhAnh(),
                doanVien.getChucVu().getId(),
                doanVien.getPhanQuyen().getId(),
                doanVien.getChiDoan().getTenChiDoan(),
                doanVien.getChucVu().getTenChucVu(),
                doanVien.getPhanQuyen().getTenQuyen(),
                doanVien.getChiDoan().getKhoa().getTenKhoa(),
                authorities
        );

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
