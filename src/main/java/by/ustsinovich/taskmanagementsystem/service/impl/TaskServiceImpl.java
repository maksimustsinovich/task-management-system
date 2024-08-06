package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.dto.TaskDto;
import by.ustsinovich.taskmanagementsystem.entity.Comment;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskPriority;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskStatus;
import by.ustsinovich.taskmanagementsystem.exception.TaskNotFoundException;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import by.ustsinovich.taskmanagementsystem.repository.TaskRepository;
import by.ustsinovich.taskmanagementsystem.service.CommentService;
import by.ustsinovich.taskmanagementsystem.service.TaskService;
import by.ustsinovich.taskmanagementsystem.specification.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final CommentService commentService;

    @Override
    public Page<Task> getAllTasks(
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    ) {
        Pageable pageable = PageRequest.of(page, size, sort.getSort());
        Specification<Task> specification = TaskSpecification.filterBy(filter);

        return taskRepository.findAll(specification, pageable);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public void deleteTaskById(Long id) {
        Task task = getTaskById(id);

        taskRepository.delete(task);
    }

    @Override
    public Task createTask(TaskDto taskDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Task task = Task
                .builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .priority(TaskPriority.valueOf(taskDto.getPriority()))
                .status(TaskStatus.valueOf(taskDto.getStatus()))
                .initiator(user)
                .build();

        return taskRepository.save(task);
    }

    @Override
    public Page<Comment> getTaskComments(
            Long id,
            Integer page,
            Integer size,
            CommentSort sort,
            CommentFilter filter
    ) {
        return commentService.getCommentsByTaskId(id, page, size, sort, filter);
    }

    @Override
    public Comment createComment(Long id, CommentDto commentDto) {
        Task task = getTaskById(id);

        return commentService.createComment(task, commentDto);
    }

    @Override
    public Task updateTask(Long id, TaskDto taskDto) {
        Task task = getTaskById(id);

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setPriority(TaskPriority.valueOf(taskDto.getPriority()));
        task.setStatus(TaskStatus.valueOf(taskDto.getStatus()));

        return taskRepository.save(task);
    }

    @Override
    public Page<Task> getExecutedTasksByUserId(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    ) {
        Pageable pageable = PageRequest.of(page, size, sort.getSort());
        Specification<Task> specification = TaskSpecification.filterBy(filter);

        return taskRepository.findByExecutorsId(id, specification, pageable);
    }

    @Override
    public Page<Task> getInitiatedTasksByUserId(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    ) {
        Pageable pageable = PageRequest.of(page, size, sort.getSort());
        Specification<Task> specification = TaskSpecification.filterBy(filter);

        return taskRepository.findByInitiatorId(id, specification, pageable);
    }

    @Override
    public Task setTaskStatus(Long id, TaskStatus taskStatus) {
        Task task = getTaskById(id);

        task.setStatus(taskStatus);

        return taskRepository.save(task);
    }

}
