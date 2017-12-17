package com.ericliu.dubbo.provider.user.dao;

import com.ericliu.dubbo.provider.auth.domain.AuthUser;
import org.springframework.ldap.core.AttributesMapper;

import java.util.List;

public abstract class AbstractLdapDao<T> {

     abstract AttributesMapper<T> getRowMapper();

    public abstract boolean save(AuthUser authUser);

    public abstract List<T> findByOu(String ou);

    public abstract T findByCn(String cn);

}
