package by.ustsinovich.taskmanagementsystem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshRequest {

    @NotBlank
    @Schema(name = "Refresh token")
    private String refreshToken;

}
