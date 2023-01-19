package edu.jundev.donation.service;

import edu.jundev.donation.dto.requests.LoginRequest;
import edu.jundev.donation.dto.requests.RegisterRequest;
import edu.jundev.donation.dto.response.ResponseJwt;
import edu.jundev.donation.dto.response.ResponseMessage;
import edu.jundev.donation.entity.BloodType;
import edu.jundev.donation.entity.Gender;
import edu.jundev.donation.entity.Role;
import edu.jundev.donation.entity.User;
import edu.jundev.donation.configuration.JwtUtils;
import edu.jundev.donation.repository.BloodRepository;
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
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GenderRepository genderRepository;
    private final BloodRepository bloodRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    public ResponseJwt authenticateUser(LoginRequest requestLogin){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestLogin.getEmail(), requestLogin.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User user = (User) authentication.getPrincipal();
        return new ResponseJwt(jwt, user.getId(), user.getEmail());
    }

    public ResponseMessage registerUser(@Valid RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            new ResponseMessage("Error: Email is already in use!");
        }
        // Create new user's account
        Gender gender = genderRepository.findByName(registerRequest.getGender()).orElseThrow(()-> new RuntimeException("Error: Gender is not found."));
        BloodType bloodType = bloodRepository.findBloodTypeByName(registerRequest.getBloodType()).orElseThrow(() -> new RuntimeException("Error : BloodType is not found."));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findRoleByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        LocalDate birthday = LocalDate.parse(registerRequest.getBirthday());
        User user = new User(registerRequest.getFirstName(),registerRequest.getLastName(),bloodType,gender,registerRequest.getEmail(),birthday,
                passwordEncoder.encode(registerRequest.getPassword()),roles);
        userRepository.save(user);
        return (new ResponseMessage("User registered successfully!"));
    }
}
