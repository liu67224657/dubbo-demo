package com.ericliu.dubbo.api.auth.dto;

import java.io.Serializable;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/11
 */
public class AuthDTO implements Serializable {
    private long id;
    private String loginName;
    private String pwd;
    private String loginDomain;
    private String pwdslat;

    public String getPwdslat() {
        return pwdslat;
    }

    public void setPwdslat(String pwdslat) {
        this.pwdslat = pwdslat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getLoginDomain() {
        return loginDomain;
    }

    public void setLoginDomain(String loginDomain) {
        this.loginDomain = loginDomain;
    }

}
