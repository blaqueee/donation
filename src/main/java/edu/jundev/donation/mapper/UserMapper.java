package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.UserDto;
import edu.jundev.donation.dto.requests.EditRequest;
import edu.jundev.donation.dto.requests.RegisterRequest;
import edu.jundev.donation.entity.User;
import edu.jundev.donation.exception.NotFoundException;
import edu.jundev.donation.repository.BloodTypeRepository;
import edu.jundev.donation.repository.GenderRepository;
import edu.jundev.donation.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final BloodTypeRepository bloodTypeRepository;
    private final GenderRepository genderRepository;
    private final RoleRepository roleRepository;
    private final GenderMapper genderMapper;
    private final BloodTypeMapper bloodTypeMapper;

    public User toEntity(RegisterRequest form) {
        return User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .middleName(form.getMiddleName())
                .email(form.getEmail())
                .bloodType(bloodTypeRepository.findById(form.getBloodTypeId())
                    .orElseThrow(() -> new NotFoundException("Blood type with id " + form.getBloodTypeId() + " not found!")))
                .gender(genderRepository.findById(form.getGenderId())
                    .orElseThrow(() -> new NotFoundException("Gender with id " + form.getGenderId() + " not found!")))
                .birthDate(form.getBirthDate())
                .password(form.getPassword())
                .roles(Set.of(roleRepository.findRoleByName("ROLE_USER")
                    .orElseThrow(() -> new NotFoundException("Role 'ROLE_USER' not found!"))))
                .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .middleName(user.getMiddleName())
                .gender(genderMapper.toDto(user.getGender()))
                .bloodType(bloodTypeMapper.toDto(user.getBloodType()))
                .email(user.getEmail())
                .build();
    }

    public User toUserFromEdit(EditRequest form) {
        return User.builder()
                .id(form.getId())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .middleName(form.getMiddleName())
                .email(form.getEmail())
                .bloodType(bloodTypeRepository.findById(form.getBloodTypeId())
                        .orElseThrow(() -> new NotFoundException("Blood type with id " + form.getBloodTypeId() + " not found!")))
                .gender(genderRepository.findById(form.getGenderId())
                        .orElseThrow(() -> new NotFoundException("Gender with id " + form.getGenderId() + " not found!")))
                .birthDate(form.getBirthDate())
                .password(form.getPassword())
                .roles(Set.of(roleRepository.findRoleByName("ROLE_USER")
                        .orElseThrow(() -> new NotFoundException("Role 'ROLE_USER' not found!"))))
                .build();
    }
}
