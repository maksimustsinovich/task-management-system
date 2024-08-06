package by.ustsinovich.taskmanagementsystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum UserSort {

    ID_ASC(Sort.by(Sort.Direction.ASC, "id")),
    ID_DESC(Sort.by(Sort.Direction.DESC, "id")),
    CREATED_ASC(Sort.by(Sort.Direction.ASC, "createdAt")),
    CREATED_DESC(Sort.by(Sort.Direction.DESC, "createdAt")),
    UPDATED_ASC(Sort.by(Sort.Direction.ASC, "updatedAt")),
    UPDATED_DESC(Sort.by(Sort.Direction.DESC, "updatedAt")),
    EMAIL_ASC(Sort.by(Sort.Direction.ASC, "email")),
    EMAIL_DESC(Sort.by(Sort.Direction.DESC, "email")),
    FIRST_NAME_ASC(Sort.by(Sort.Direction.ASC, "firstName")),
    FIRST_NAME_DESC(Sort.by(Sort.Direction.DESC, "firstName")),
    LAST_NAME_ASC(Sort.by(Sort.Direction.ASC, "lastName")),
    LAST_NAME_DESC(Sort.by(Sort.Direction.DESC, "lastName")),
    PATRONYMIC_ASC(Sort.by(Sort.Direction.ASC, "patronymic")),
    PATRONYMIC_DESC(Sort.by(Sort.Direction.DESC, "patronymic"));

    private final Sort sort;

}
