package com.example.Java_API.repository;

import com.example.Java_API.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(final String name);
    List<User> findByNameContaining(final String name);
    List<User> findByNameContains(final String name);
    @Query(nativeQuery = true, value = "SELECT * FROM java_demo.users where name like :name")
    List<User> findNameByQuerry(final String name);
}
