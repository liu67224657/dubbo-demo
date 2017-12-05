package com.ericliu.dubbo.api.user.dto;

import java.io.Serializable;


/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/11/28
 */
public class UserDTO implements Serializable {
    private long id;
    private String name;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
