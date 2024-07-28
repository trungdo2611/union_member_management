package com.laptrinhwebjava.QLDoanVien.controller;

import com.laptrinhwebjava.QLDoanVien.jwt.JwtTokenProvider;
import com.laptrinhwebjava.QLDoanVien.payload.request.loginRequest;
import com.laptrinhwebjava.QLDoanVien.payload.response.JwtResponse;
import com.laptrinhwebjava.QLDoanVien.security.customUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class SecurityController {
    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody loginRequest login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        customUser user = (customUser)authentication.getPrincipal();
        String jwt = jwtTokenProvider.generateToken(user);

        List<String> roles = user.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
       return ResponseEntity.ok( new JwtResponse(
               jwt,
               user.getId(),
               user.getMaDoanVien(),
               user.getTenDoanVien(),
               user.getNgaySinh(),
               user.getGioiTinh(),
               user.getDanToc(),
               user.getEmail(),
               user.getDienThoai(),
               user.getNgayVaoDoan(),
               user.getPassword(),
               user.getHinhAnh(),
               user.getChucVu_id(),
               user.getPhanQuyen_id(),
               user.getTenChiDoan(),
               user.getTenChucVu(),
               user.getTenPhanQuyen(),
               user.getTenKhoa(),
               roles
               ));
    }


    @PostMapping("/logAdmin")
    public ResponseEntity<?> loginAdmin(@RequestBody loginRequest login) {
        try {
            // Xác thực người dùng
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

            // Lấy thông tin người dùng sau khi xác thực thành công
            customUser user = (customUser) authentication.getPrincipal();

            // Kiểm tra vai trò của người dùng
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            boolean isAdmin = authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

            // Nếu người dùng có vai trò là "ROLE_ADMIN", tạo token JWT
            if (isAdmin) {
                String jwt = jwtTokenProvider.generateToken(user);

                // Trả về thông tin người dùng và token JWT
                return ResponseEntity.ok().body(new JwtResponse(
                        jwt,
                        user.getId(),
                        user.getMaDoanVien(),
                        user.getTenDoanVien(),
                        user.getNgaySinh(),
                        user.getGioiTinh(),
                        user.getDanToc(),
                        user.getEmail(),
                        user.getDienThoai(),
                        user.getNgayVaoDoan(),
                        user.getPassword(),
                        user.getHinhAnh(),
                        user.getChucVu_id(),
                        user.getPhanQuyen_id(),
                        user.getTenChiDoan(),
                        user.getTenChucVu(),
                        user.getTenPhanQuyen(),
                        user.getTenKhoa(),
                        authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
                ));
            } else {
                // Nếu người dùng không có vai trò là "ROLE_ADMIN", trả về lỗi 403 Forbidden
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bạn không đủ quyền truy cập!");
            }
        } catch (AuthenticationException e) {
            // Nếu đăng nhập thất bại, trả về lỗi 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Đăng nhập thất bại! Vui lòng kiểm tra email và mật khẩu.");
        }
    }

}
