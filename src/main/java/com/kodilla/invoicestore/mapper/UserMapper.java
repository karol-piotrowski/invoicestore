package com.kodilla.invoicestore.mapper;

import com.kodilla.invoicestore.domain.User;
import com.kodilla.invoicestore.dto.UserDto;
import com.kodilla.invoicestore.service.EmailConfigService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
@NoArgsConstructor
public class UserMapper {
    @Autowired
    private EmailConfigService emailConfigService;

    public UserDto mapUserToUserDto(final User user) {
        UserDto userDto = UserDto.builder()
                .userId(user.getUserId())
                .login(user.getLogin())
                .taxId(user.getTaxId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .build();
        if(!isNull(user.getEmailConfig())) {
            userDto.setEmailConfigId(user.getEmailConfig().getEmailConfigId());
        }
        return userDto;
    }

    public User mapUserDtoToUser(final UserDto userDto) {
        User user = User.builder()
                .userId(userDto.getUserId())
                .login(userDto.getLogin())
                .taxId(userDto.getTaxId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .build();
        if(!isNull(userDto.getEmailConfigId())) {
            user.setEmailConfig(emailConfigService.getEmailConfig(userDto.getEmailConfigId()).orElse(null));
        }
        return user;

    }

    public List<UserDto> mapUsersListToUserDtosList(final List<User> users) {
        return users.stream()
                .map(user -> mapUserToUserDto(user))
                .collect(Collectors.toList());
    }

    public List<User> mapUserDtosListToUsersList(final List<UserDto> userDtos) {
        return userDtos.stream()
                .map(userDto -> mapUserDtoToUser(userDto))
                .collect(Collectors.toList());
    }
}
