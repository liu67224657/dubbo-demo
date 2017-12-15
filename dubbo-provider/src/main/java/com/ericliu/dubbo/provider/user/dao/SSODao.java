package com.ericliu.dubbo.provider.user.dao;

import javax.naming.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;

import javax.naming.directory.Attributes;

import static org.springframework.ldap.query.LdapQueryBuilder.query;


import java.util.List;

@Component
public class SSODao {

    private LdapTemplate ldapTemplate;


    public SSODao(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }


    public List<String> getAllPersonNames() {
        return ldapTemplate.search(
                query().where("objectclass").is("person"),
                new AttributesMapper<String>() {
                    public String mapFromAttributes(Attributes attrs)
                            throws NamingException {
                        return attrs.get("cn").get().toString();
                    }
                });
    }
}
