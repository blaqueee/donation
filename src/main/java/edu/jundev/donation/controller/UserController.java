package edu.jundev.donation.controller;

import edu.jundev.donation.dto.UserDto;
import edu.jundev.donation.dto.requests.UserEditRequest;
import edu.jundev.donation.entity.User;
import edu.jundev.donation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/edit")
    ResponseEntity<UserDto> editUser(@Valid @ModelAttribute UserEditRequest userEditRequest,
                                     @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.updateUser(userEditRequest, user));
    }
}
