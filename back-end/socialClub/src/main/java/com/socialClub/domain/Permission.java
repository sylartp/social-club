package com.socialClub.domain;

import lombok.Data;

/**
 * Created by peng.tian on 2018/3/26
 */
@Data
public class Permission {

    private int id;
    private String name;

    public Permission(){}

    public Permission(int id, String name){
        this.id = id;
        this.name = name;
    }
}
