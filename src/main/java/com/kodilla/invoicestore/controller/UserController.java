package com.kodilla.invoicestore.controller;

import com.kodilla.invoicestore.dto.UserDto;
import com.kodilla.invoicestore.mapper.UserMapper;
import com.kodilla.invoicestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<UserDto> getUsers() {
        return userMapper.mapUsersListToUserDtosList(userService.getUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userMapper.mapUserToUserDto(userService.getUser(userId).get());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userMapper.mapUserToUserDto(userService.saveUser(userMapper.mapUserDtoToUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        userService.saveUser(userMapper.mapUserDtoToUser(userDto));
    }


}
