package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.UserInfoDto;
import edu.jundev.donation.entity.User;
import edu.jundev.donation.entity.UserActivation;
import edu.jundev.donation.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoMapper {
    private final StatusMapper statusMapper;
    private final UserMapper userMapper;
    private final BloodTypeMapper bloodTypeMapper;
    private final GenderMapper genderMapper;

    public UserInfo toUserInfoFromRegister(UserActivation activation, User savedUser) {
        return UserInfo.builder()
                .user(savedUser)
                .amountOfDonations(0)
                .points(0)
                .status(statusMapper.initialStatus())
                .gender(activation.getGender())
                .bloodType(activation.getBloodType())
                .build();
    }

    public UserInfoDto toDto(UserInfo userInfo) {
        return UserInfoDto.builder()
                .id(userInfo.getId())
                .userDto(userMapper.toDto(userInfo.getUser()))
                .bloodTypeDto(bloodTypeMapper.toDto(userInfo.getBloodType()))
                .genderDto(genderMapper.toDto(userInfo.getGender()))
                .statusDto(statusMapper.toDto(userInfo.getStatus()))
                .amountOfDonations(userInfo.getAmountOfDonations())
                .points(userInfo.getPoints())
                .build();
    }
}