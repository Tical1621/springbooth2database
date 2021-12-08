package com.example.springbooth2database.repository;

import com.example.springbooth2database.entity.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final DataSource dataSource;

    public UserRepositoryCustomImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> nativeUpdate(User user) throws SQLException {
        String sql = "update users set age=?,name=? where id =?";


        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement updateSql = connection.prepareStatement(sql)) {
           // TODO зачем явным образом выключать автокоммит. Тебе не обязательно следить вручную за транзакцией
            connection.setAutoCommit(false);
            // TODO Объект для зависи не валидируется. Если в setLong передать null объект будет ошибка
            updateSql.setLong(1, user.getAge());
            updateSql.setString(2, user.getName());
            updateSql.setLong(3, user.getId());
            updateSql.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            // TODO аккуратное логирование
            e.printStackTrace();

        }
        // TODO зачем null если в сигнатуре Optional
        return null;
    }
}

