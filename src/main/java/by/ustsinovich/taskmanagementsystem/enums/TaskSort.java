package by.ustsinovich.taskmanagementsystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum TaskSort {

    ID_ASC(Sort.by(Sort.Direction.ASC, "id")),
    ID_DESC(Sort.by(Sort.Direction.DESC, "id")),
    CREATED_ASC(Sort.by(Sort.Direction.ASC, "createdAt")),
    CREATED_DESC(Sort.by(Sort.Direction.DESC, "createdAt")),
    UPDATED_ASC(Sort.by(Sort.Direction.ASC, "updatedAt")),
    UPDATED_DESC(Sort.by(Sort.Direction.DESC, "updatedAt")),
    STATUS_ASC(Sort.by(Sort.Direction.ASC, "status")),
    STATUS_DESC(Sort.by(Sort.Direction.DESC, "status")),
    PRIORITY_ASC(Sort.by(Sort.Direction.ASC, "priority")),
    PRIORITY_DESC(Sort.by(Sort.Direction.DESC, "priority")),
    TITLE_ASC(Sort.by(Sort.Direction.ASC, "title")),
    TITLE_DESC(Sort.by(Sort.Direction.DESC, "title"));

    private final Sort sort;

}
