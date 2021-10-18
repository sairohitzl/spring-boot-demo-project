package com.springboot.moviescrud.dto;

import com.springboot.moviescrud.entity.Authority;
import com.springboot.moviescrud.entity.Review;
import lombok.Data;
import lombok.ToString;


import javax.persistence.*;
import java.util.List;

@Data
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String username;
    private String password;
    private int enabled;

    @ToString.Exclude

    private List<Review> reviews;

    private Authority theAuthority;

}
