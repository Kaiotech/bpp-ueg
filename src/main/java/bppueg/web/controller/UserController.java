package bppueg.web.controller;



import bppueg.services.UserService;
import bppueg.web.mappers.UserMapper;
import bppueg.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class UserController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;
    @Autowired
    private  final UserService userService;

    @Autowired
    private final UserMapper mapper;

    @GetMapping(produces = {"application/json"}, path = "users")
    public ResponseEntity<UserDto> getUsers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                     @RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "email", required = false) String email) {
        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        UserDto userDtoList = (UserDto) userService.getUsers(username, email, PageRequest.of(pageNumber, pageSize));

        return new ResponseEntity<>(userDtoList, HttpStatus.OK);

    }

    @GetMapping("users/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") UUID id) {

        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<UserDto> createUser(@RequestBody @Validated UserDto userDto) {

        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") UUID id, @RequestBody @Validated UserDto userDto) {

        return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.ACCEPTED);
    }
}
