package com.ericliu.dubbo.webapi.web.rest;

import com.ericliu.dubbo.api.auth.dto.AuthUserDTO;
import com.ericliu.dubbo.api.auth.service.AuthService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/12
 */
@Controller
@RequestMapping
public class IndexResouce {
    private static final Logger logger = LoggerFactory.getLogger(IndexResouce.class);


    private final AuthService authService;

    public IndexResouce(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        logger.info("------没有权限-------");
        return "403";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        AuthUserDTO authUserDTO = new AuthUserDTO();
        String cn = "liu67224657"+System.currentTimeMillis();
        String ou = "managers";
        String sn = "Eric Liu";
        String password = "123456";
        authUserDTO.setCn(cn);
        authUserDTO.setOu(ou);
        authUserDTO.setSn(sn);
        authUserDTO.setUserPassword(password);

        authService.saveAuthUser(authUserDTO);

        List<AuthUserDTO> dtoList = authService.findAuthUserDTOByOu(ou);
        System.out.println("find by ou:" + dtoList);

        AuthUserDTO dto = authService.findAuthUserByCn(cn);
        System.out.println("find by cn:" + dto);
//        String userName = (String) SecurityUtils.getSubject().getPrincipal();
//        model.addAttribute("username", userName);
        return "index";
    }

    @RequestMapping
    public String defaultIndex(Model model) {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("username", userName);
        return "index";
    }

}
