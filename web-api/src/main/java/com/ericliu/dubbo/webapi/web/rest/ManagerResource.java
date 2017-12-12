package com.ericliu.dubbo.webapi.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/12
 */
@RestController
@RequestMapping("/manager")
public class ManagerResource {

    @GetMapping("/update")
    public ResponseEntity<String> update() throws URISyntaxException {


        return ResponseEntity.ok("manager success");
    }

    @GetMapping("/save")
    public ResponseEntity<String> save() throws URISyntaxException {
        return ResponseEntity.ok("manager success");
    }
}
