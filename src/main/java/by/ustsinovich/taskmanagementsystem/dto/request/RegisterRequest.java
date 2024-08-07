package by.ustsinovich.taskmanagementsystem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank
    @Email
    @Schema(description = "Email address")
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    @Schema(description = "Password")
    private String password;

    @NotBlank
    @Size(max = 255)
    @Schema(description = "First name")
    private String firstName;

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Patronymic")
    private String patronymic;

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Last name")
    private String lastName;

}