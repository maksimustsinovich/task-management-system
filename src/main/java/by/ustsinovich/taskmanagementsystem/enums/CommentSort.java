package by.ustsinovich.taskmanagementsystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

/**
 * Enum for sorting comments.
 */
@Getter
@RequiredArgsConstructor
public enum CommentSort {

    /**
     * Sort by ID in ascending order.
     */
    ID_ASC(Sort.by(Sort.Direction.ASC, "id")),

    /**
     * Sort by ID in descending order.
     */
    ID_DESC(Sort.by(Sort.Direction.DESC, "id")),

    /**
     * Sort by creation date in ascending order.
     */
    CREATED_ASC(Sort.by(Sort.Direction.ASC, "createdAt")),

    /**
     * Sort by creation date in descending order.
     */
    CREATED_DESC(Sort.by(Sort.Direction.DESC, "createdAt")),

    /**
     * Sort by update date in ascending order.
     */
    UPDATED_ASC(Sort.by(Sort.Direction.ASC, "updatedAt")),

    /**
     * Sort by update date in descending order.
     */
    UPDATED_DESC(Sort.by(Sort.Direction.DESC, "updatedAt"));

    private final Sort sort;

}