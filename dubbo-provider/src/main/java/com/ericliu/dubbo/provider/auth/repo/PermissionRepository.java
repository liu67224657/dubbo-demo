package com.ericliu.dubbo.provider.auth.repo;

import com.ericliu.dubbo.api.auth.enumration.PermissionRole;
import com.ericliu.dubbo.provider.auth.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/11
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> findAllByRole(PermissionRole role);
}
