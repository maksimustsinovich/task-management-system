package by.ustsinovich.taskmanagementsystem.mapper;

import by.ustsinovich.taskmanagementsystem.dto.TaskDto;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TaskMapper {

    @Mapping(source = "initiator", target = "initiator")
    TaskDto mapToDto(Task task);

}
