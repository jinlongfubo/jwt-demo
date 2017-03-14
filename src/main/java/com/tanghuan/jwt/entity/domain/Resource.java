package com.tanghuan.jwt.entity.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Arthur on 2017/3/14.
 */

@Entity
@Table(name = "t_resource")
public class Resource extends SuperEntity {
    @Column(unique = true)
    private String name;

    // 默认为可用状态 true，false为不可用状态
    @Column(name = "enabled", columnDefinition = "bit(1) default 1")
    private boolean enabled = true;

    // 资源分类 OPERATION 表示模块操作  HADOOP_HDFS Hadoop中hdfs的操作命令
    private String type;

    // 填写一些中文说明
    private String comment;

    @ManyToMany(targetEntity = Permission.class, mappedBy = "resources")
    private List<Permission> permissions;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
