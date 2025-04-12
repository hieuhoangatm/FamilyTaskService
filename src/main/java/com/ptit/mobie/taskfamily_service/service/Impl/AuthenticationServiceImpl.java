package com.ptit.mobie.taskfamily_service.service.Impl;

import com.ptit.mobie.taskfamily_service.dto.request.LoginRequest;
import com.ptit.mobie.taskfamily_service.dto.request.RegisterRequest;
import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.LoginResponse;
import com.ptit.mobie.taskfamily_service.jwt.JwtService;
import com.ptit.mobie.taskfamily_service.model.User;
import com.ptit.mobie.taskfamily_service.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ptit.mobie.taskfamily_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public BaseResponse register(RegisterRequest request) {
        // Kiểm tra username đã tồn tại chưa
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // Kiểm tra email đã tồn tại chưa
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Tạo user mới
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword())) // Mã hóa mật khẩu
                .email(request.getEmail())
                .gender(request.getGender())
                .phone(request.getPhone())
                .role(request.getRole() != null ? request.getRole() : "USER") // Mặc định role là USER
                .language(request.getLanguage())
                .total_points(0) // Điểm mặc định là 0
                .isEnabled(true)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .build();

        userRepository.save(user);
        return BaseResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(user)
                .message("Create user account successfully")
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        // Xác thực người dùng bằng AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Lấy thông tin người dùng từ Authentication
        User user = (User) authentication.getPrincipal();

        // Tạo JWT token
        String token = jwtService.generateToken(user.getUsername(), user.getRole());

        // Trả về phản hồi đăng nhập
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        return response;
    }
}
