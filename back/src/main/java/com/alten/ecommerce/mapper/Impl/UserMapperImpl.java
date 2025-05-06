package com.alten.ecommerce.mapper.Impl;

import com.alten.ecommerce.dtos.UserDto;
import com.alten.ecommerce.entities.User;
import com.alten.ecommerce.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFirstname(userDto.getFirstname());
        user.setEmail(userDto.getEmail());
        //user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPassword(userDto.getPassword());
        return user;
    }

    @Override
    public UserDto userToUserDto(User user) {
        UserDto dto = new UserDto();
       // dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstname(user.getFirstname());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }

}
