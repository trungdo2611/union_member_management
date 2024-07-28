package com.laptrinhwebjava.QLDoanVien.controller;

import com.laptrinhwebjava.QLDoanVien.jwt.JwtTokenProvider;
import com.laptrinhwebjava.QLDoanVien.security.customUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class testController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private customUserService userService;
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String userAccess() {
        return "User Content.";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin() {
        return "Admin Board.";
    }
//private String getJwtFromRequest(HttpServletRequest request) {
//    String headerAuth = request.getHeader("Authorization");
//    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
//        return headerAuth.substring(7);
//    }
//
//    return null;
//}
//    @GetMapping
//    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
//        // Lấy token từ header của request
//        String jwt = getJwtFromRequest(request);
//
//        // Giải mã thông tin từ token
//        String email = jwtTokenProvider.getUserNameFromJwtToken(jwt);
//
//        // Truy vấn thông tin người dùng từ service
//        UserDetails userDetails = userService.loadUserByUsername(email);
//
//        // Trả về thông tin người dùng
//        return ResponseEntity.ok(userDetails);
//    }
}
