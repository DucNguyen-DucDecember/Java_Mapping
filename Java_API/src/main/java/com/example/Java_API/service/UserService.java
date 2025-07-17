package com.example.Java_API.service;

import com.example.Java_API.enity.User;

import java.util.List;

public interface UserService {
    User save(final User user);
    List<User> findAll();
    User findById(final Long id);
    List<User> findByName(final String name);
    User update(final User newUser);
    Boolean delete(final Long id);
}
