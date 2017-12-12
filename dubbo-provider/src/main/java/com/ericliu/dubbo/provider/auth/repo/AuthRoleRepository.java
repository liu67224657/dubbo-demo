package com.ericliu.dubbo.provider.auth.repo;

import com.ericliu.dubbo.provider.auth.domain.Auth;
import com.ericliu.dubbo.provider.auth.domain.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/11
 */
@Repository
public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {

    List<AuthRole> findAllByUid(long uid);
}
