package com.frnz7.restSpring.repository;

import com.frnz7.restSpring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {



}
