package by.ustsinovich.taskmanagementsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String content;

}
