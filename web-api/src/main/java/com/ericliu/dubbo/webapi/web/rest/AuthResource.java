package com.ericliu.dubbo.webapi.web.rest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/11
 */
@Controller
@RequestMapping("/api/auth")
public class AuthResource {

    private static final Logger logger = LoggerFactory.getLogger(IndexResouce.class);

    @GetMapping("/login")
    public String loginForm(Model model, HttpServletRequest request) {
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        if(savedRequest==null){
            model.addAttribute("redurl", "/");
        }
        String url = savedRequest.getRequestUrl();
        if (url!=null) {
            model.addAttribute("redurl", url);
            logger.info("登录认证通过跳转到：" + url);
        }else{
            model.addAttribute("redurl", "/");
        }

        return "/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String loginname, @RequestParam String pwd,
                        @RequestParam String redurl,
                        HttpServletRequest request) throws URISyntaxException {
        UsernamePasswordToken token = new UsernamePasswordToken(loginname, pwd);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        String username = loginname;
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            ResponseEntity.badRequest().header("message", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            ResponseEntity.badRequest().header("message", "密码不正确");
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            ResponseEntity.badRequest().header("message", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            ResponseEntity.badRequest().header("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            ResponseEntity.badRequest().header("message", "用户名或密码不正确");
        }

        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            SavedRequest url = WebUtils.getSavedRequest(request);
            System.out.println(url);
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)跳转到：" + url);

//           return "redirect:"+url;
            return "redirect:"+redurl;
        } else {
            token.clear();
            return "redirect:/api/auth/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes) {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/api/auth/login";
    }


}
