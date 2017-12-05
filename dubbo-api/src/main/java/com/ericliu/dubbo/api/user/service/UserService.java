package com.ericliu.dubbo.api.user.service;

import com.ericliu.dubbo.api.user.dto.UserDTO;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/11/28
 */
public interface UserService {

     UserDTO save(UserDTO user);
}
