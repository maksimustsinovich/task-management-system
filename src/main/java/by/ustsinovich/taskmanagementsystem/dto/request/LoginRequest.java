package by.ustsinovich.taskmanagementsystem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank
    @Email
    @Schema(name = "Email")
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    @Schema(name = "Password")
    private String password;

}
