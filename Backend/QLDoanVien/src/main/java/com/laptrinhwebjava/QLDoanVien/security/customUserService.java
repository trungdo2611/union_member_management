package com.laptrinhwebjava.QLDoanVien.security;

import com.laptrinhwebjava.QLDoanVien.model.DoanVien;
import com.laptrinhwebjava.QLDoanVien.responsitory.DoanVienRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class customUserService implements UserDetailsService {
    @Autowired
    private final DoanVienRepo doanVienRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DoanVien doanVien = doanVienRepo.findByEmail(username);
        if(doanVien == null) {
            throw new UsernameNotFoundException("Không tìm thấy đoàn viên");
        }
        return customUser.mapDoanVienToCustomUser(doanVien);
    }
}
