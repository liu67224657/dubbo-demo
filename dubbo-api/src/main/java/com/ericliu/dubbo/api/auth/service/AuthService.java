package com.ericliu.dubbo.api.auth.service;

import com.ericliu.dubbo.api.auth.dto.AuthDTO;
import com.ericliu.dubbo.api.auth.dto.AuthInfoDTO;
import com.ericliu.dubbo.api.auth.enumration.PermissionRole;

import javax.security.auth.AuthPermission;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/11/30
 */
public interface AuthService {

    AuthDTO saveAuth(AuthDTO authDTO);

    AuthDTO findAuthDTO(String longName);

    AuthInfoDTO findAuthInfoByLoginName(String loginName);

}
