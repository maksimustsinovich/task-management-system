package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.entity.Comment;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.enums.TaskPriority;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskStatus;
import by.ustsinovich.taskmanagementsystem.exception.TaskNotFoundException;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import by.ustsinovich.taskmanagementsystem.repository.TaskRepository;
import by.ustsinovich.taskmanagementsystem.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void getAllTasks() {
        // given
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setDescription("Description 1");
        task1.setPriority(TaskPriority.BLOCKER);
        task1.setStatus(TaskStatus.IN_PROGRESS);

        Task task2 = new Task();
        task2.setTitle("Task 2");
        task2.setDescription("Description 2");
        task2.setPriority(TaskPriority.BLOCKER);
        task2.setStatus(TaskStatus.IN_PROGRESS);

        TaskFilter filter = new TaskFilter();
        filter.setStatus(TaskStatus.IN_PROGRESS);

        Page<Task> tasks = new PageImpl<>(List.of(task1));

        when(taskRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(tasks);

        // when
        Page<Task> result = taskService.getAllTasks(0, 10, TaskSort.ID_ASC, filter);

        // then
        assertEquals(1, result.getTotalElements());
        assertEquals(task1, result.getContent().get(0));
    }

    @Test
    void getTaskById() {
        // given
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description 1");
        task.setPriority(TaskPriority.BLOCKER);
        task.setStatus(TaskStatus.IN_PROGRESS);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // when
        Task result = taskService.getTaskById(1L);

        // then
        assertEquals(task, result);
    }

    @Test
    void getTaskById_ThrowsTaskNotFoundException() {
        // given
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        // when and then
        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(1L));
    }

    @Test
    void deleteTaskById() {
        // given
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description 1");
        task.setPriority(TaskPriority.BLOCKER);
        task.setStatus(TaskStatus.IN_PROGRESS);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // when
        taskService.deleteTaskById(1L);

        // then
        verify(taskRepository, times(1)).delete(task);
    }

    @Test
    void createComment() {
        // given
        Long taskId = 1L;
        CommentDto commentDto = new CommentDto();
        commentDto.setContent("Comment 1");

        Task task = new Task();
        task.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Comment comment = new Comment();
        when(commentService.createComment(task, commentDto)).thenReturn(comment);

        // when
        Comment result = taskService.createComment(taskId, commentDto);

        // then
        assertEquals(comment, result);
    }

}