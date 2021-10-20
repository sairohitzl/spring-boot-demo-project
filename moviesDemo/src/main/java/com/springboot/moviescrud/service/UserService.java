package com.springboot.moviescrud.service;

import com.springboot.moviescrud.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findById(int theId);

    public void save(User theUser);

    public void deleteById(int theId);

    public User findByUsername(String s);

    public boolean usernameAlreadyExists(String email);
}
