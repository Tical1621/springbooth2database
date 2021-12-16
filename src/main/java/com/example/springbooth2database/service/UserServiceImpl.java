package com.example.springbooth2database.service;

import com.example.springbooth2database.controller.rest.UserDto;
import com.example.springbooth2database.entity.User;
import com.example.springbooth2database.repository.UserRepository;
import com.example.springbooth2database.repository.UserRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService { //чтобы скрыть конвертацию от контроллера
    private final UserRepository userRepo;
    private final UserRepositoryCustom userRepoCustom;


    @Override
    public UserDto save(UserDto user) {
        User userEntity = convertToEntity(user);//convert Dto to entity
        Optional<User> user1;//=null
        try {
            user1 = userRepoCustom.nativeUpdate(userEntity);
            if (user1.isPresent()) {//if user exist do convert
                return convertToDto(user1.get());
            }
        } catch (SQLException e) {
            log.error("Error message", e);
        }
        throw new RuntimeException("Save not complete");

    }

    private User convertToEntity(UserDto user) {
        User user1 = new User();
        user1.setId(user.getId());
        user1.setAge(user.getAge());
        user1.setName(user.getName());
        return user1;
    }

    private UserDto convertToDto(User user) {
        UserDto user1 = new UserDto();
        user1.setId(user.getId());
        user1.setAge(user.getAge());
        user1.setName(user.getName());
        return user1;
    }


}
