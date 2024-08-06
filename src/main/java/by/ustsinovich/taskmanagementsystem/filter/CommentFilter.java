package by.ustsinovich.taskmanagementsystem.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Filter for comments")
public class CommentFilter {

    @Schema(description = "Comment content")
    private String content;

    @Schema(description = "Task ID")
    private Long taskId;

    @Schema(description = "Author ID")
    private Long authorId;

}