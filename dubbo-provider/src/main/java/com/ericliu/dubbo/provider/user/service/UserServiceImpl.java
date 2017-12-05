package com.ericliu.dubbo.provider.user.service;

import com.ericliu.dubbo.api.user.dto.UserDTO;
import com.ericliu.dubbo.api.user.service.UserService;
import com.ericliu.dubbo.provider.user.amqp.UserAmqpSender;
import com.ericliu.dubbo.provider.user.dao.UserDao;
import com.ericliu.dubbo.provider.user.domain.User;
import com.ericliu.dubbo.provider.user.repo.UserRepoistrey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/11/28
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private final UserRepoistrey userRepoistrey;
    private UserAmqpSender userAmqp;

    public UserServiceImpl(UserRepoistrey userRepoistrey, UserAmqpSender userAmqp) {
        this.userRepoistrey = userRepoistrey;
        this.userAmqp = userAmqp;

    }

    /**
     * jpa
     * @param user
     * @return
     */
    public UserDTO save(UserDTO user) {
        User userDoamin = new User();
        userDoamin.setName(user.getName());
        userDoamin = userRepoistrey.save(userDoamin);
        user.setId(userDoamin.getId());

        userAmqp.sendMessage(userDoamin);
        return user;
    }

//    public UserDTO save(UserDTO user) {
//        User userDoamin = new User();
//        userDoamin.setName(user.getName());
//        userDoamin = userDao.insertUser(userDoamin);
//        user.setId(userDoamin.getId());
//
//        userAmqp.sendMessage(userDoamin);
//        return user;
//    }
}
