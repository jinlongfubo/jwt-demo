package com.tanghuan.jwt.entity.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanghuan on 2017/3/8.
 */

@Entity
@Table(name = "t_role")
public class Role extends SuperEntity {

    @Column(unique = true, nullable = false)
    private String name;

    private boolean enabled = true;

    @ManyToMany(targetEntity = User.class, mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
