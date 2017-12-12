package com.ericliu.dubbo.api.auth.dto;

import com.ericliu.dubbo.api.auth.enumration.PermissionRole;

import java.io.Serializable;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/11
 */
public class AuthRoleDTO implements Serializable {
    private long id;
    private String uid;
    private PermissionRole role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public PermissionRole getRole() {
        return role;
    }

    public void setRole(PermissionRole role) {
        this.role = role;
    }
}
