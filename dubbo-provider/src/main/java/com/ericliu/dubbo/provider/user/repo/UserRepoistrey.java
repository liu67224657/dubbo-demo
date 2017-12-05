package com.ericliu.dubbo.provider.user.repo;

import com.ericliu.dubbo.provider.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/4
 */
@Repository
public interface UserRepoistrey extends JpaRepository<User, Long> {
}
