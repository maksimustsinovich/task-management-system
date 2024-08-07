package by.ustsinovich.taskmanagementsystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

/**
 * Enum for sorting tasks.
 */
@Getter
@RequiredArgsConstructor
public enum TaskSort {

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
    UPDATED_DESC(Sort.by(Sort.Direction.DESC, "updatedAt")),

    /**
     * Sort by status in ascending order.
     */
    STATUS_ASC(Sort.by(Sort.Direction.ASC, "status")),

    /**
     * Sort by status in descending order.
     */
    STATUS_DESC(Sort.by(Sort.Direction.DESC, "status")),

    /**
     * Sort by priority in ascending order.
     */
    PRIORITY_ASC(Sort.by(Sort.Direction.ASC, "priority")),

    /**
     * Sort by priority in descending order.
     */
    PRIORITY_DESC(Sort.by(Sort.Direction.DESC, "priority")),

    /**
     * Sort by title in ascending order.
     */
    TITLE_ASC(Sort.by(Sort.Direction.ASC, "title")),

    /**
     * Sort by title in descending order.
     */
    TITLE_DESC(Sort.by(Sort.Direction.DESC, "title"));

    private final Sort sort;

}
