package com.ericliu.dubbo.webapi.configure;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.codec.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/12
 */
public class EricCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String[] accountCredentialsArray = getCredentials(info).toString().split(":");
        String accountCredentials=accountCredentialsArray[0];


        String pwd =String.valueOf(token.getPassword());// 判断一下密码是否是用户输入的，还是JCIS传过来的
        if(pwd.length() == 32){
            return equals(pwd, accountCredentials); //密码长度=32位，说明是md5加密过，是从xx传进来的 32位加密。
        }
        MessageDigest md5= null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            byte[] ret=md5.digest((pwd+accountCredentialsArray[1]).getBytes("UTF-8"));
            return equals(Hex.encodeToString(ret),accountCredentials);
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
