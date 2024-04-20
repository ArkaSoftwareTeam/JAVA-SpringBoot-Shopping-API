package app.shopapi.DTOs;

import lombok.Data;

@Data
public class JWTAuthRequestDTO {
    private String username;  // email
    private String password;
}
