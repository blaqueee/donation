package edu.jundev.donation.controller;

import edu.jundev.donation.dto.requests.LoginRequest;
import edu.jundev.donation.dto.requests.RegisterRequest;
import edu.jundev.donation.dto.response.ResponseJwt;
import edu.jundev.donation.dto.response.ResponseMessage;
import edu.jundev.donation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/login")
    public ResponseJwt authenticateUser(@Valid @RequestBody LoginRequest requestLogin){
        return userService.authenticateUser(requestLogin);
    }
    @PostMapping(path="/register")
    public ResponseMessage registerUser(@Valid @RequestBody RegisterRequest requestSignup){
        return userService.registerUser(requestSignup);
    }
}
