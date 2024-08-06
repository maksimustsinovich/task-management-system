package by.ustsinovich.taskmanagementsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {

    private Long id;

    private String title;

    private String description;

    private String priority;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    UserDto initiator;

}
