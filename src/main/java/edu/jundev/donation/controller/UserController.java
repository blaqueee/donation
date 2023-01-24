package edu.jundev.donation.controller;

import edu.jundev.donation.dto.UserDto;
import edu.jundev.donation.dto.requests.EditRequest;
import edu.jundev.donation.entity.User;
import edu.jundev.donation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/edit")
    ResponseEntity<UserDto> editUser (@Valid @RequestBody EditRequest editRequest,
                                      @AuthenticationPrincipal User user){
        return  ResponseEntity.ok(userService.updateUser(editRequest,user));
    }
}
