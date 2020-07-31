package com.santosglaiton.cursomc.controller;

import com.santosglaiton.cursomc.dto.EmailDTO;
import com.santosglaiton.cursomc.security.JWTUtil;
import com.santosglaiton.cursomc.security.UserSS;
import com.santosglaiton.cursomc.service.AuthService;
import com.santosglaiton.cursomc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService service;

    @PostMapping("/refresh_token")
    public ResponseEntity<Void> refreshToekn(HttpServletResponse response){
        UserSS user = UserService.authenticated();
        String toekn = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + toekn);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto){
        service.sendNewPassword(objDto.getEmail());
        return ResponseEntity.noContent().build();
    }
}
