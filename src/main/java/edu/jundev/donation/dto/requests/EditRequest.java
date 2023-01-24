package edu.jundev.donation.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditRequest {

    @NotBlank
    @JsonProperty("id")
    private Long  id;

    @NotBlank
    @Email
    @JsonProperty("email")
    private String email;

    @NotBlank
    @Size(min = 5, max = 50)
    @JsonProperty("password")
    private String password;

    @NotBlank
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank
    @JsonProperty("middle_name")
    private String middleName;

    @NotBlank
    @JsonProperty("gender_id")
    private Long genderId;

    @NotNull
    @JsonProperty("blood_type_id")
    private Long bloodTypeId;

    @NotNull
    @JsonProperty("birth_date")
    private LocalDate birthDate;
}
