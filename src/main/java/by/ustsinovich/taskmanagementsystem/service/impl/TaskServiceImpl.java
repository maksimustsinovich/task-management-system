package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.dto.TaskDto;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.exception.TaskNotFoundException;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import by.ustsinovich.taskmanagementsystem.mapper.TaskMapper;
import by.ustsinovich.taskmanagementsystem.repository.TaskRepository;
import by.ustsinovich.taskmanagementsystem.service.TaskService;
import by.ustsinovich.taskmanagementsystem.specification.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    @Override
    public Page<TaskDto> getAllTasks(Integer page, Integer size, TaskSort sort, TaskFilter filter) {
        Pageable pageable = PageRequest.of(page, size, sort.getSort());
        Specification<Task> specification = TaskSpecification.filterBy(filter);

        return taskRepository
                .findAll(specification, pageable)
                .map(taskMapper::mapToDto);
    }

    @Override
    public TaskDto getTaskById(Long id) {
        return taskRepository
                .findById(id)
                .map(taskMapper::mapToDto)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public void deleteTaskById(Long id) {
        Task task = taskRepository
                .findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskRepository.delete(task);
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        return null;
    }

    @Override
    public Page<CommentDto> getTaskComments(
            Long id,
            Integer page,
            Integer size,
            CommentSort sort,
            CommentFilter filter
    ) {
        return null;
    }

    @Override
    public CommentDto createComment(Long id, CommentDto commentDto) {
        return null;
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        return null;
    }

    @Override
    public Page<TaskDto> getExecutedTasksByUserId(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    ) {
        return null;
    }

}
