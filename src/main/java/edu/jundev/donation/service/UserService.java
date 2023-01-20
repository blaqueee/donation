package edu.jundev.donation.service;

import edu.jundev.donation.configuration.JwtUtils;
import edu.jundev.donation.dto.UserDto;
import edu.jundev.donation.dto.requests.LoginRequest;
import edu.jundev.donation.dto.requests.RegisterRequest;
import edu.jundev.donation.dto.response.ResponseJwt;
import edu.jundev.donation.entity.User;
import edu.jundev.donation.exception.EmailExistsException;
import edu.jundev.donation.exception.NotFoundException;
import edu.jundev.donation.mapper.UserMapper;
import edu.jundev.donation.repository.BloodTypeRepository;
import edu.jundev.donation.repository.GenderRepository;
import edu.jundev.donation.repository.RoleRepository;
import edu.jundev.donation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GenderRepository genderRepository;
    private final BloodTypeRepository bloodTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;

    public ResponseJwt authenticateUser(LoginRequest requestLogin){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestLogin.getEmail(), requestLogin.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User user = (User) authentication.getPrincipal();
        return new ResponseJwt(jwt, user.getId(), user.getEmail());
    }

    public UserDto registerUser(@Valid RegisterRequest registerRequest) throws NotFoundException, EmailExistsException {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailExistsException("Error: Email is already in use!");
        }
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        User user = userMapper.toEntity(registerRequest);
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }
}
