package demo.com.vn.authen.service;

import demo.com.vn.authen.dto.login.request.LoginRequest;
import demo.com.vn.authen.dto.login.request.RegisterRequest;
import demo.com.vn.authen.dto.login.response.JwtResponse;
import demo.com.vn.authen.entity.User;
import demo.com.vn.authen.repository.UserRepository;
import demo.com.vn.authen.security.JwtUtil;
import demo.com.vn.authen.util.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @SneakyThrows
    public void register(@RequestBody RegisterRequest request) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(request.getUsername())))
            throw new Exception("Error: Username is already taken!");

        if (Boolean.TRUE.equals(userRepository.existsByEmail(request.getEmail())))
            throw new Exception("Error: Email is already in use!");

        Role role;
        try {
            role = Role.valueOf(request.getRole().toUpperCase());
        } catch (Exception e) {
            throw new Exception("Error: Role is invalid!");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }

    @SneakyThrows
    public JwtResponse login(@RequestBody LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String jwt = jwtUtil.generateToken(userDetails);
            return new JwtResponse(jwt);
        } catch (BadCredentialsException e) {
            throw new Exception("Error: Invalid username or password");
        }
    }
}
