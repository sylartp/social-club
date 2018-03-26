package com.socialClub.domain;

import lombok.Data;

import java.util.Set;

/**
 * Created by peng.tian on 2018/3/23
 */
@Data
public class Role {

    private int id;
    private String name;
    private Set<Permission> permissions;

    public Role(){}

    public Role(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Role(int id, String name, Set<Permission> permissions){
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }
}
