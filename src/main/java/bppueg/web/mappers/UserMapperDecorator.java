package bppueg.web.mappers;

import bppueg.entity.UserEntity;
import bppueg.web.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class UserMapperDecorator implements UserMapper {

    private UserMapper mapper;

    @Autowired
    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDto userToUserDto(UserEntity user) {
        return mapper.userToUserDto(user);
    }

    @Override
    public UserEntity userDtoToUser(UserDto userDto) {
        return mapper.userDtoToUser(userDto);
    }
}
