package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.dto.TaskDto;
import by.ustsinovich.taskmanagementsystem.entity.Comment;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskStatus;
import by.ustsinovich.taskmanagementsystem.exception.TaskNotFoundException;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import by.ustsinovich.taskmanagementsystem.repository.TaskRepository;
import by.ustsinovich.taskmanagementsystem.service.CommentService;
import by.ustsinovich.taskmanagementsystem.service.TaskService;
import by.ustsinovich.taskmanagementsystem.specification.TaskSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;

    private final CommentService commentService;

    @Override
    public Page<Task> getAllTasks(
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    ) {
        logger.info("Getting all tasks with filter: {}", filter);
        Pageable pageable = PageRequest.of(page, size, sort.getSort());
        Specification<Task> specification = TaskSpecification.filterBy(filter);

        return taskRepository.findAll(specification, pageable);
    }

    @Override
    public Task getTaskById(Long id) {
        logger.info("Getting task by ID: {}", id);
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteTaskById(Long id) {
        logger.info("Deleting task with ID: {}", id);
        Task task = getTaskById(id);

        taskRepository.delete(task);
    }

    @Override
    @Transactional
    public Task createTask(TaskDto taskDto) {
        logger.info("Creating new task");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Task task = Task
                .builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .priority(taskDto.getPriority())
                .status(taskDto.getStatus())
                .initiator(user)
                .build();

        return taskRepository.save(task);
    }

    @Override
    public Page<Comment> getTaskComments(
            Long id,
            Integer page,
            Integer size,
            CommentSort sort
    ) {
        logger.info("Getting comments for task with ID: {}", id);
        return commentService.getCommentsByTaskId(id, page, size, sort);
    }

    @Override
    @Transactional
    public Comment createComment(Long id, CommentDto commentDto) {
        logger.info("Creating new comment for task with ID: {}", id);
        Task task = getTaskById(id);

        return commentService.createComment(task, commentDto);
    }

    @Override
    @Transactional
    public Task updateTask(Long id, TaskDto taskDto) {
        logger.info("Updating task with ID: {}", id);
        Task task = getTaskById(id);

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setPriority(taskDto.getPriority());
        task.setStatus(taskDto.getStatus());

        return taskRepository.save(task);
    }

    @Override
    public Page<Task> getExecutedTasksByUserId(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort
    ) {
        logger.info("Getting executed tasks for user with ID: {}", id);
        Pageable pageable = PageRequest.of(page, size, sort.getSort());

        return taskRepository.findByExecutorId(id, pageable);
    }

    @Override
    public Page<Task> getInitiatedTasksByUserId(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort
    ) {
        logger.info("Getting initiated tasks for user with ID: {}", id);
        Pageable pageable = PageRequest.of(page, size, sort.getSort());

        return taskRepository.findByInitiatorId(id, pageable);
    }

    @Override
    @Transactional
    public Task setTaskStatus(Long id, TaskStatus taskStatus) {
        logger.info("Setting task status to {} for task with ID: {}", taskStatus, id);
        Task task = getTaskById(id);

        task.setStatus(taskStatus);

        return taskRepository.save(task);
    }

}