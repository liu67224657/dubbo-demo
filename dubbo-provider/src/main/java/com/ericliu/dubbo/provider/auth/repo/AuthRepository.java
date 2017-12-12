package com.ericliu.dubbo.provider.auth.repo;

import com.ericliu.dubbo.provider.auth.domain.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/11
 */
@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {

    Auth findByLoginName(String loginName);
}
