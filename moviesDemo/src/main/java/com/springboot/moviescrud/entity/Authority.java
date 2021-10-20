package com.springboot.moviescrud.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name="authorities")
public class Authority {

    @Id
    @NotNull
    @Column(name="authorities_id")
    private int authorityId;


    @Column(name = "authority")
    private  String authorityRole;


    @ToString.Exclude
    @OneToMany(mappedBy="theAuthority",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<User> users;

    public Authority(){
    }

    public Authority(int authorityId) {
        this.authorityId = authorityId;
    }
}
