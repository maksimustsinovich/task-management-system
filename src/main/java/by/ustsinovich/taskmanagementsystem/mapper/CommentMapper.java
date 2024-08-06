package by.ustsinovich.taskmanagementsystem.mapper;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto mapToDto(Comment comment);

}