package com.springboot.moviescrud.dao;

import com.springboot.moviescrud.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
