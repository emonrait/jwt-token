package com.cbl.jwttoken.controller;


import com.cbl.jwttoken.dto.LoginReq;
import com.cbl.jwttoken.dto.LoginRes;
import com.cbl.jwttoken.dto.User;
import com.cbl.jwttoken.exception.ResponseHandler;
import com.cbl.jwttoken.filter.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/rest/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<?> merchantLogin(@Valid @RequestBody LoginReq loginReq) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUserName(), loginReq.getPassword()));
            String username = authentication.getName();
            User user = new User();
            user.setUserName(username);
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(username, token);

            return ResponseHandler.generateResponse(
                    "Merchant Login Success. ",
                    "100",
                    HttpStatus.OK,
                    loginRes);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(
                    "Merchant Login Failed. " + ex.getMessage(),
                    "101",
                    HttpStatus.NOT_FOUND,
                    null);
        }
    }
}
