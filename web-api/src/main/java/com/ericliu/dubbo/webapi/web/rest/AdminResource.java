package com.ericliu.dubbo.webapi.web.rest;

import com.ericliu.dubbo.api.auth.dto.AuthDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/12
 */
@RestController
@RequestMapping("/admin")
public class AdminResource {
    private static final Logger logger = LoggerFactory.getLogger(AdminResource.class);

    @GetMapping("/update")
    public ResponseEntity<String> update() throws URISyntaxException {

        logger.info("update need perms[admin:update]");
        return ResponseEntity.ok("update success");
    }

    @GetMapping("/save")
    public ResponseEntity<String> save() throws URISyntaxException {
        logger.info("update need perms[admin:insert]");
        return ResponseEntity.ok("save success");
    }

    @GetMapping("/get")
    public ResponseEntity<String> get() throws URISyntaxException {
        logger.info("update need roles[ADMIN]");
        return ResponseEntity.ok("get success");
    }
}
