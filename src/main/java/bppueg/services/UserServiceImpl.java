package bppueg.services;

import bppueg.entity.UserEntity;
import bppueg.repositories.UserRepository;
import bppueg.web.controller.NotFoundException;
import bppueg.web.mappers.UserMapper;
import bppueg.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    @Override
    public UserDto getById(UUID id) {

        return mapper.userToUserDto(userRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        if (userRepository.findAllByUsername(userDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User already exist");
        } else if (userRepository.findAllByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User already exist");
        }

        UserEntity userEntity = mapper.userDtoToUser(userDto);
        userRepository.save(userEntity);
        return mapper.userToUserDto(userEntity);
    }

    @Override
    public UserDto updateUser(UUID id, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(NotFoundException::new);

        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());

        return mapper.userToUserDto(userRepository.save(userEntity));
    }

    @Override
    public List<UserDto> getUsers(String username, String email, PageRequest pageRequest) {

        Optional<UserEntity> userEntity;

        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(email)) {
            userEntity = userRepository.findAllByUsernameAndEmail(username, email);
        } else if (!StringUtils.isEmpty(username) && StringUtils.isEmpty(email)) {
            userEntity = userRepository.findAllByUsername(username);
        } else if (StringUtils.isEmpty(username) && !StringUtils.isEmpty(email)) {
            userEntity = userRepository.findAllByEmail(email);
        }


        return null;
    }
}
