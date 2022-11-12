package bppueg.services;

import bppueg.entity.UserEntity;
import bppueg.repositories.UserRepository;
import bppueg.web.mappers.UserMapper;
import bppueg.web.mappers.UserMapperDecorator;
import bppueg.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    @Override
    public UserDto getById(UUID id) throws ChangeSetPersister.NotFoundException {
        return mapper.userToUserDto(userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return null;
    }

    @Override
    public List<UserDto> getUsers(String username, String email, PageRequest pageRequest) {
//        List<UserDto> userDtoList;
//        Page<UserEntity> userEntities;
//
//        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(email)) {
//            userEntities = userRepository.findAllByUsernameAndEmail(username, email, pageRequest);
//        } else if (!StringUtils.isEmpty(username) && StringUtils.isEmpty(email)) {
//            userEntities = userRepository.findAllByUsername(username, pageRequest);
//        } else if (StringUtils.isEmpty(username) && !StringUtils.isEmpty(email)) {
//            userEntities = userRepository.findAllByEmail(email, pageRequest);
//        } else {
//            userEntities = userRepository.findAll(pageRequest);
//        }
        List<UserDto> userDtoList = new ArrayList<UserDto>();
        Pageable pageable = PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize());
        Page<UserEntity> userEntities = userRepository.findAll(pageable);

        List<UserEntity> userEntityList = userEntities.getContent();

        for (UserEntity userEntity : userEntityList) {
            UserDto userDto = mapper.userToUserDto(userEntity);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}
