package com.ericliu.dubbo.webapi.web.rest;

import com.ericliu.dubbo.api.user.dto.UserDTO;
import com.ericliu.dubbo.api.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/11/29
 */
@RestController
@RequestMapping("/api/user")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser() throws URISyntaxException {

        UserDTO user = new UserDTO();
        user.setName("liuhao");
        user = userService.save(user);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable long id) throws URISyntaxException {
        UserDTO user = userService.get(id);
        return ResponseEntity.ok(user);
    }


}
