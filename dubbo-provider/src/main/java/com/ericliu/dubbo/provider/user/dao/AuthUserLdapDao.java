package com.ericliu.dubbo.provider.user.dao;

import com.ericliu.dubbo.provider.auth.domain.AuthUser;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Component
public class AuthUserLdapDao extends AbstractLdapDao<AuthUser> {

    private LdapTemplate ldapTemplate;

    public AuthUserLdapDao(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public boolean save(AuthUser authUser) {
        BasicAttribute ocattr = new BasicAttribute("objectclass");
        ocattr.add("top");
        ocattr.add("organizationalPerson");

        Attributes attrs = new BasicAttributes();
        attrs.put(ocattr);
        attrs.put("cn", authUser.getCn());
        attrs.put("sn", authUser.getSn());
        attrs.put("ou", authUser.getOu());
        attrs.put("userPassword", authUser.getUserPassword());
        ldapTemplate.bind("cn=" + authUser.getCn(), null, attrs);
        return true;
    }

    public List<AuthUser> findByOu(String ou) {
        LdapQuery query = query().where("objectclass").is("organizationalPerson")
                .and("ou").is(ou);
        List<AuthUser> list = ldapTemplate.search(query, getRowMapper());
        return list;
    }

    public AuthUser findByCn(String cn) {
        LdapQuery query = query().where("objectclass").is("organizationalPerson")
                .and("cn").is(cn);
        List<AuthUser> list = ldapTemplate.search(query, getRowMapper());
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }


    protected AttributesMapper<AuthUser> getRowMapper() {
        return new AttributesMapper<AuthUser>() {
            public AuthUser mapFromAttributes(Attributes attrs)
                    throws NamingException {
                AuthUser authUser = new AuthUser();
                authUser.setCn(attrs.get("cn").get().toString());
                authUser.setSn(attrs.get("sn").get().toString());
                authUser.setOu(attrs.get("ou").get().toString());
                if (attrs.get("userPassword") != null) {
                    authUser.setUserPassword(attrs.get("userPassword").get().toString());
                }
                return authUser;
            }
        };
    }

}
