package com.ericliu.dubbo.provider.auth.service;

import com.ericliu.dubbo.api.auth.service.AuthService;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/11/30
 */
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean auth(String name) {
        System.out.println("now auth success。name:" + name);
        return true;
    }
}
