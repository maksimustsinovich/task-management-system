package by.ustsinovich.taskmanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Comment data transfer object")
public class CommentDto {

    @Schema(description = "Unique identifier of the comment")
    private Long id;

    @Schema(description = "Date and time when the comment was created")
    private LocalDateTime createdAt;

    @Schema(description = "Date and time when the comment was last updated")
    private LocalDateTime updatedAt;

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Content of the comment")
    private String content;

}
