package edu.jundev.donation.controller;

import edu.jundev.donation.dto.UserDto;
import edu.jundev.donation.dto.requests.LoginRequest;
import edu.jundev.donation.dto.requests.RegisterRequest;
import edu.jundev.donation.dto.response.ResponseJwt;
import edu.jundev.donation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<ResponseJwt> authenticateUser(@Valid @RequestBody LoginRequest requestLogin){
        return ResponseEntity.ok(userService.authenticateUser(requestLogin));
    }
    @PostMapping(path="/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody RegisterRequest requestSignup){
        return new ResponseEntity<>(userService.registerUser(requestSignup), HttpStatus.OK);
    }
}
