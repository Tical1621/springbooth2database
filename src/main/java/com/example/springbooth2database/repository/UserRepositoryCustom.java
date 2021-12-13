package com.example.springbooth2database.repository;

import com.example.springbooth2database.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> nativeUpdate(User user) throws SQLException;
}