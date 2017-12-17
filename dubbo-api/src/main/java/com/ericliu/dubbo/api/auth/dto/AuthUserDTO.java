package com.ericliu.dubbo.api.auth.dto;

import java.io.Serializable;

/**
 * 用户ldap
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/17
 */
public class AuthUserDTO  implements Serializable{
    private String cn;
    private String sn;
    private String ou;
    private String userPassword;

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getOu() {
        return ou;
    }

    public void setOu(String ou) {
        this.ou = ou;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "AuthUserDTO{" +
                "cn='" + cn + '\'' +
                ", sn='" + sn + '\'' +
                ", ou='" + ou + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
