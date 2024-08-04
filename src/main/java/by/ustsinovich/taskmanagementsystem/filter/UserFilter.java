package by.ustsinovich.taskmanagementsystem.filter;

import by.ustsinovich.taskmanagementsystem.enums.UserRole;
import lombok.Data;

@Data
public class UserFilter {

    private String email;

    private String firstName;

    private String patronymic;

    private String lastName;

    private UserRole role;

}
