package com.ericliu.dubbo.provider.auth.domain;

import com.ericliu.dubbo.api.auth.enumration.PermissionRole;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/11
 */
@Entity
@Table(name = "t_auth_role")
public class AuthRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uid")
    private long uid;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private PermissionRole role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public PermissionRole getRole() {
        return role;
    }

    public void setRole(PermissionRole role) {
        this.role = role;
    }
}
