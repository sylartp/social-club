package com.socialClub.domain;

import lombok.Data;

import java.util.Set;


/**
 * Created by peng.tian on 2018/3/1
 */
@Data
public class User {

    private int id;
    private String email;
    private String username;
    private String password;
    private Set<Role> roles;

    public User(){}

    public User(int id, String email, String username, String password){
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(int id, String email, String username, String password, Set<Role> roles){
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.username = username;
        this.password = password;
    }

}
