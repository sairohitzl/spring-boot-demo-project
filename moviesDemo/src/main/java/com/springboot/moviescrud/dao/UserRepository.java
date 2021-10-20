package com.springboot.moviescrud.dao;


import com.springboot.moviescrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUsername(String s);

}
