package com.example.BanRyeohaedyuo.repository;

import com.example.BanRyeohaedyuo.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);
    Optional<User> findUserByUserName(String userName);
}
