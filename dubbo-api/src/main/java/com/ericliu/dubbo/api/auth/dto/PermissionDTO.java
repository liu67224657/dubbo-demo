package com.ericliu.dubbo.api.auth.dto;

import com.ericliu.dubbo.api.auth.enumration.PermissionRole;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/11
 */
public class PermissionDTO {
    private long id;
    private String name;
    private PermissionRole authRole;
    private String permission;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PermissionRole getAuthRole() {
        return authRole;
    }

    public void setAuthRole(PermissionRole authRole) {
        this.authRole = authRole;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authRole=" + authRole +
                '}';
    }
}
