package com.tanghuan.jwt.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Arthur on 2017/3/9.
 */
public class JwtAuthenToken implements Authentication, Serializable{

    private String token;

    private List<GrantedAuthorityImpl> authorities;

    public JwtAuthenToken() {
    }

    public JwtAuthenToken(String token) {
        this.token = token;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public Object getCredentials() {
        return null;
    }

    @JsonIgnore
    @Override
    public Object getDetails() {
        return null;
    }

    @JsonIgnore
    @Override
    public Object getPrincipal() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @JsonIgnore
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @JsonIgnore
    @Override
    public String getName() {
        return null;
    }

    public String getToken() {
        return token;
    }
}
