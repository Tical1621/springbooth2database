package com.example.springbooth2database.Repository;

import com.example.springbooth2database.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
}
