package bppueg.web.mappers;

import bppueg.entity.UserEntity;
import bppueg.web.model.UserDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    UserDto userToUserDto(UserEntity user);

    UserEntity userDtoToUser(UserDto userDto);
}
