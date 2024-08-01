package com.example.server.login;

import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public CsrfToken csrf(CsrfToken csrfToken) {
        return csrfToken;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        Authentication authRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
        Authentication authResponse = this.authenticationManager.authenticate(authRequest);
        System.out.println("authResponse: " + authResponse);

        return ResponseEntity.ok(addTokenCookie(response, authResponse.getName(), authResponse.getAuthorities().iterator().next().toString()));

    }

    private static LoginResponse addTokenCookie(HttpServletResponse response, String username, String role) {
        String token = JwtUtil.generateToken(username, role);
        System.out.println("token: " + token);

        Cookie tokenCookie = new Cookie("accessToken", token);
        tokenCookie.setHttpOnly(true);
        tokenCookie.setSecure(true);
        tokenCookie.setPath("/");
        tokenCookie.setMaxAge((int) JwtUtil.EXPIRATION_TIME/1000);

        response.addCookie(tokenCookie);
        return new LoginResponse(
                username,
                role,
                formatDateToISO(JwtUtil.extractExpiration(token))
        );
    }

    private static String formatDateToISO(Date date) {
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        return isoFormat.format(date);
    }


    public record LoginRequest(String username, String password) {}
    public record LoginResponse(String username, String role, String expires) {}

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie tokenCookie = new Cookie("accessToken", "");
        tokenCookie.setHttpOnly(true);
        tokenCookie.setSecure(true);
        tokenCookie.setPath("/");
        tokenCookie.setMaxAge(0);
        response.addCookie(tokenCookie);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request, HttpServletResponse response) {
        String token = JwtUtil.getTokenFromCookie(request);
        String username = JwtUtil.extractUsername(token);
        String role = JwtUtil.extractRole(token);
        return ResponseEntity.ok(addTokenCookie(response, username, role));
    }
}
