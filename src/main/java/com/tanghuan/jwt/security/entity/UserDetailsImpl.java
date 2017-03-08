package com.tanghuan.jwt.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by tanghuan on 2017/3/8.
 */
public class UserDetailsImpl implements UserDetails {

    private String username;

    private String password;

    private boolean enabled;

    private List<GrantedAuthorityImpl> authorities;

    public UserDetailsImpl() {
    }

    public UserDetailsImpl(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDetailsImpl(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public UserDetailsImpl(String username, String password, boolean enabled, List<GrantedAuthorityImpl> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthorityImpl> authorities) {
        this.authorities = authorities;
    }
}
