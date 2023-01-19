package edu.jundev.donation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.jundev.donation.entity.BloodType;
import edu.jundev.donation.entity.Gender;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("middleName")
    private String middleName;

    @JsonProperty("bloodType")
    private BloodType bloodType;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("email")
    private String email;

//    @JsonProperty("role")
//    private String role;
}