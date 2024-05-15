package demo.com.vn.controller;

import demo.com.vn.dto.request.AuthenticationRequest;
import demo.com.vn.dto.request.IntrospectRequest;
import demo.com.vn.dto.request.LogoutRequest;
import demo.com.vn.dto.request.RefreshRequest;
import demo.com.vn.dto.response.AuthenticationResponse;
import demo.com.vn.dto.response.IntrospectResponse;
import demo.com.vn.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/token")
    public Object authenticate(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/introspect")
    IntrospectResponse authenticate(@RequestBody IntrospectRequest request) {
        return authenticationService.introspect(request);
    }

    @PostMapping("/refresh")
    AuthenticationResponse authenticate(@RequestBody RefreshRequest request) {
        return authenticationService.refreshToken(request);
    }

    @PostMapping("/logout")
    void logout(@RequestBody LogoutRequest request) {
        authenticationService.logout(request);
    }

}
