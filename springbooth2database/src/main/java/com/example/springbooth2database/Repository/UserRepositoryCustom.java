package com.example.springbooth2database.Repository;

import com.example.springbooth2database.Entity.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserRepositoryCustom {
     Optional<User> nativeUpdate(User user)throws SQLException;
}