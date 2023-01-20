package edu.jundev.donation.dto.response;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ResponseJwt {
    private String accessToken;
    private String tokenType = "Bearer";
    private Long id;
    private String email;

    public ResponseJwt(String accessToken,Long id, String email) {
        this.accessToken = accessToken;
        this.id = id;
        this.email = email;
    }

}
