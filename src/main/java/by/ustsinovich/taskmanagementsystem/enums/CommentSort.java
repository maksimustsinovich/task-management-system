package by.ustsinovich.taskmanagementsystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum CommentSort {

    ID_ASC(Sort.by(Sort.Direction.ASC, "id")),
    ID_DESC(Sort.by(Sort.Direction.DESC, "id")),
    CREATED_ASC(Sort.by(Sort.Direction.ASC, "createdAt")),
    CREATED_DESC(Sort.by(Sort.Direction.DESC, "createdAt")),
    UPDATED_ASC(Sort.by(Sort.Direction.ASC, "updatedAt")),
    UPDATED_DESC(Sort.by(Sort.Direction.DESC, "updatedAt"));

    private final Sort sort;

}
