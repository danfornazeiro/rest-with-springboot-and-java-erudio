package com.frnz7.restSpring.repository;

import com.frnz7.restSpring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
