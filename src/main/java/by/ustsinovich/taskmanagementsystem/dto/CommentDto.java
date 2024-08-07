package by.ustsinovich.taskmanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Comment data transfer object")
public class CommentDto {

    @Schema(description = "Unique identifier of the comment", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Date and time when the comment was created", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @Schema(description = "Date and time when the comment was last updated", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;

    @NotBlank(message = "Content is required")
    @Size(max = 255, message = "Content must not exceed 255 characters")
    @Schema(description = "Content of the comment")
    private String content;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UserDto author;

}
