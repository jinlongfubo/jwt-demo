package com.tanghuan.jwt.security.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by tanghuan on 2017/3/8.
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    private boolean enabled;

    public GrantedAuthorityImpl() {
    }

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public GrantedAuthorityImpl(String authority, boolean enabled) {
        this.authority = authority;
        this.enabled = enabled;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
