package bppueg.services;

import bppueg.web.model.UserDto;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto getById(UUID id);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UUID id, UserDto userDto);

    List<UserDto> getUsers(String username, String email, PageRequest pageRequest);

}
