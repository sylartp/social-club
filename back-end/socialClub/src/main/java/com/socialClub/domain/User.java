package com.socialClub.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by peng.tian on 2018/3/1
 */
@Data
public class User implements UserDetails {

    private int id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public User(int id, String username, String password){
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password, Collection<? extends GrantedAuthority> authorities){
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
