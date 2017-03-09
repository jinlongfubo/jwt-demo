package com.tanghuan.jwt.security.entity;

import java.io.Serializable;

/**
 * Created by Arthur on 2017/3/9.
 */
public class JwtAuthenToken implements Serializable{

    private String token;

    public JwtAuthenToken() {
    }

    public JwtAuthenToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
