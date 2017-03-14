package com.tanghuan.jwt.entity.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Arthur on 2017/3/14.
 */

@Entity
@Table(name = "t_permission")
public class Permission extends SuperEntity {
    @Column(unique = true)
    private String name;

    private String type;

    // 默认为可用状态 true，false为不可用状态
    @Column(name = "enabled", columnDefinition = "bit(1) default 1")
    private boolean enabled = true;

    @ManyToMany(targetEntity = Role.class, mappedBy = "permissions")
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "m_permission_resource",
            joinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")})
    private List<Resource> resources;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
