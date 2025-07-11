package demo.com.vn.authen.service;

import demo.com.vn.authen.dto.login.request.LoginRequest;
import demo.com.vn.authen.dto.login.request.RefreshRequest;
import demo.com.vn.authen.dto.login.request.RegisterRequest;
import demo.com.vn.authen.dto.login.response.TokenResponse;
import demo.com.vn.authen.entity.User;
import demo.com.vn.authen.repository.UserRepository;
import demo.com.vn.authen.security.JwtUtil;
import demo.com.vn.authen.util.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public TokenResponse register(RegisterRequest request) {
        if (userRepo.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepo.existsByEmail(request.getUsername())) {
            throw new RuntimeException("Email already exists");
        }

        saveUserInfo(request);

        String accessToken = JwtUtil.generateAccessToken(request.getUsername(), request.getRole());
        String refreshToken = JwtUtil.generateRefreshToken(request.getUsername());
        return new TokenResponse(accessToken, refreshToken);
    }

    @SneakyThrows
    public TokenResponse login(LoginRequest request) {
        Optional<User> userOpt = userRepo.findByUsername(request.getUsername());
        if (userOpt.isEmpty() || !authenticate(request.getUsername(), request.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        User user = userOpt.get();
        String accessToken = JwtUtil.generateAccessToken(user.getUsername(), user.getRole());
        String refreshToken = JwtUtil.generateRefreshToken(user.getUsername());
        return new TokenResponse(accessToken, refreshToken);
    }

    public TokenResponse refresh(RefreshRequest request) {
        if (!JwtUtil.isTokenValid(request.getRefreshToken())) {
            throw new RuntimeException("Invalid refresh token");
        }
        String username = JwtUtil.getUsernameFromToken(request.getRefreshToken());
        Role role = JwtUtil.getRoleFromToken(request.getRefreshToken());
        String newAccessToken = JwtUtil.generateAccessToken(username, role);
        return new TokenResponse(newAccessToken, request.getRefreshToken());
    }

    public void saveUserInfo(RegisterRequest request) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User user = User.builder()
                .username(request.getUsername())
                .password(hashedPassword)
                .email(request.getEmail())
                .role(request.getRole())
                .build();
        userRepo.save(user);
    }

    public boolean authenticate(String username, String rawPassword) {
        Optional<User> userOpt = userRepo.findByUsername(username);
        if (userOpt.isEmpty()) {
            return false;
        }
        User user = userOpt.get();
        // Compare raw password with hashed password stored in DB
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
