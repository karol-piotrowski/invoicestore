package com.kodilla.invoicestore.mapper;

import com.kodilla.invoicestore.domain.User;
import com.kodilla.invoicestore.dto.UserDto;
import com.kodilla.invoicestore.service.EmailConfigService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    @Autowired
    private EmailConfigService emailConfigService;

    public UserDto mapUserToUserDto(final User user) {
        return new UserDto(user.getUserId(),
                user.getLogin(),
                user.getTaxId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmailConfig().getEmailConfigId()
        );
    }

    public User mapUserDtoToUser(final UserDto userDto) {
        return new User(userDto.getUserId(),
                userDto.getLogin(),
                userDto.getTaxId(),
                userDto.getFirstname(),
                userDto.getLastname(),
                emailConfigService.getEmailConfig(userDto.getEmailConfigId()).get()
        );
    }

    public List<UserDto> mapUsersListToUserDtosList(final List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getUserId(),
                        user.getLogin(),
                        user.getTaxId(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getEmailConfig().getEmailConfigId()))
                .collect(Collectors.toList());
    }

    public List<User> mapUserDtosListToUsersList(final List<UserDto> userDtos) {
        return userDtos.stream()
                .map(userDto -> new User(userDto.getUserId(),
                        userDto.getLogin(),
                        userDto.getTaxId(),
                        userDto.getFirstname(),
                        userDto.getLastname(),
                        emailConfigService.getEmailConfig(userDto.getEmailConfigId()).get()))
                .collect(Collectors.toList());
    }
}
