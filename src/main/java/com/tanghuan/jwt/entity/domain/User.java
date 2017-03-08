package com.tanghuan.jwt.entity.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanghuan on 2017/3/8.
 */

@Entity
@Table(name = "t_user")
public class User extends SuperEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String password;

    private boolean enabled = true;

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(
            name = "m_user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})

    private List<Role> roles = new ArrayList<>();

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
