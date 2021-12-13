package com.example.springbooth2database.service;

import com.example.springbooth2database.controller.rest.UserDto;
import org.springframework.stereotype.Repository;



public interface UserService {
    UserDto save(UserDto user);
}
