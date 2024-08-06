package by.ustsinovich.taskmanagementsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String firstName;

    private String patronymic;

    private String lastName;

    private String email;

    private String role;

}
