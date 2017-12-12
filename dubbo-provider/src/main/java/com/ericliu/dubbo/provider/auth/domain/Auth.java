package com.ericliu.dubbo.provider.auth.domain;

import com.ericliu.dubbo.api.auth.enumration.LoginDomain;

import javax.persistence.*;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/11
 */
@Entity
@Table(name = "t_auth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login_name")
    private String loginName;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "login_domain")
    @Enumerated(EnumType.STRING)
    private LoginDomain loginDomain = LoginDomain.MOBILE;

    @Column(name = "pwdslat")
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

    public LoginDomain getLoginDomain() {
        return loginDomain;
    }

    public void setLoginDomain(LoginDomain loginDomain) {
        this.loginDomain = loginDomain;
    }
}
