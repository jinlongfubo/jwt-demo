package com.tanghuan.jwt.security.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by tanghuan on 2017/3/8.
 */
public class AuthenToken implements Serializable {

    @NotBlank(message = "USERNAME_NULL")
    private String username;

    @NotBlank(message = "PASSWORD_NULL")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
