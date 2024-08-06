package by.ustsinovich.taskmanagementsystem.mapper;

import by.ustsinovich.taskmanagementsystem.dto.UserDto;
import by.ustsinovich.taskmanagementsystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapToDto(User user);

}
