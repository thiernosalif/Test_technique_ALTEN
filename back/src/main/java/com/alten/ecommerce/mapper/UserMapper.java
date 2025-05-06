package com.alten.ecommerce.mapper;

import com.alten.ecommerce.dtos.UserDto;
import com.alten.ecommerce.entities.User;

public interface UserMapper {

    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
    /*User userDtoToUser(UserDto userDto);

    @Mapping(target = "password", ignore = true)
    UserDto userToUserDto(User user);*/
}
