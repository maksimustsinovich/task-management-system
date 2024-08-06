package by.ustsinovich.taskmanagementsystem.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {

    private String email;

    private String password;

    private String firstName;

    private String patronymic;

    private String lastName;

}
