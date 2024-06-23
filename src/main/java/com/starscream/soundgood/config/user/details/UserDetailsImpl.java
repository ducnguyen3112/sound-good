package com.starscream.soundgood.config.user.details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starscream.soundgood.entities.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public UserDetailsImpl(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;

    }

    public static UserDetailsImpl build(AppUser user) {

        return new UserDetailsImpl(user.getId(), user.getUsername(), user.getPassword());
    }
}
