package by.ustsinovich.taskmanagementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@Schema(description = "User data transfer object")
public class UserDto {

    @Schema(description = "Unique identifier of the user", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Date and time when the user was created", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @Schema(description = "Date and time when the user was last updated", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;

    @NotBlank
    @Size(max = 255)
    @Schema(description = "First name of the user")
    private String firstName;

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Patronymic of the user")
    private String patronymic;

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Last name of the user")
    private String lastName;

    @NotBlank
    @Email
    @Schema(description = "Email address of the user")
    private String email;

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Role of the user")
    private String role;

}