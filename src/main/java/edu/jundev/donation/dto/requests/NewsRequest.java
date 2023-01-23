package edu.jundev.donation.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @JsonProperty("image")
    @NotNull
    private MultipartFile image;

    @NotBlank
    private String text;
}
