package com.yty.controller;

import com.yty.Vo.LoginResult;
import com.yty.entity.LoginData;
import com.yty.entity.User;
import com.yty.service.UserService;
import com.yty.utils.RSAUtils;
import com.yty.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public LoginResult Login(
            @RequestBody User user1)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        LoginResult result = new LoginResult();
        LoginData loginData = new LoginData();
        String username = user1.getUsername();
        String password = user1.getPassword();
        User user = userService.getUserByName(username);
        boolean f = userService.adminBool(String.valueOf(user.getId()));
        if(f==false){
            result.setMsg("无权限");
            result.setStatus(200);
            return result;
        }

        if (user == null) {
            result.setMsg("无此用户，请重新输入正确用户名");
            result.setStatus(2008);
        } else {
            if (RSAUtils.privateDecrypt(user.getPassword(), RSAUtils.getPrivateKey(user.getPravitekey().trim())).equals(password)) {
                result.setStatus(1010);
                String token = TokenUtil.token(String.valueOf(user.getId()));
                loginData.setToken(token);
                result.setMsg("登录成功");
                result.setData(loginData);
                result.setEmail(user.getEmail());
                result.setName(user.getUsername());
            } else {
                result.setStatus(2007);
                result.setMsg("密码错误");
            }
        }
        return result;
    }
}
