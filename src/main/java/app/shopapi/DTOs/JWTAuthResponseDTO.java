package app.shopapi.DTOs;

import lombok.Data;

@Data
public class JWTAuthResponseDTO {
    private String token;

    private UserDTO user;
}
