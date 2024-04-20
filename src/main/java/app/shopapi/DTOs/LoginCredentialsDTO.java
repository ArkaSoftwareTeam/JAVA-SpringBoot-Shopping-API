package app.shopapi.DTOs;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCredentialsDTO {
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private String password;
}
