package com.example.springbooth2database.repository;

import com.example.springbooth2database.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;


@Repository
@Slf4j
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final DataSource dataSource;

    public UserRepositoryCustomImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> nativeUpdate(User user) throws SQLException {
        if (user.getId() == null) {
            throw new IllegalArgumentException("userId must not be null");
        }
        String sql = "update users set age=?,name=? where id =?";

        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement updateSql = connection.prepareStatement(sql)) {
            updateSql.setInt(1, user.getAge());
            updateSql.setString(2, user.getName());
            updateSql.setLong(3, user.getId());
            updateSql.executeUpdate();
            return Optional.of(user);//if update success return user
        } catch (SQLException e) {
            log.error("Update error", e);
            throw e;
        }
    }
}

