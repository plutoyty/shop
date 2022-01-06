package com.yty.controller;

import com.yty.entity.LoginData;
import com.yty.res.LoginResult;
import com.yty.res.RegResult;
import com.yty.service.UserService;
import com.yty.utils.DateUtil;
import com.yty.utils.RSAUtils;
import com.yty.utils.TokenUtil;
import com.yty.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("user")
public class LogRegController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String test() {
        System.out.println("test");
        userService.print();
        return "test";
    }

    @RequestMapping("/register")
    public RegResult register(
            @RequestBody User user)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        RegResult result = new RegResult();
//        System.out.println(user.getUsername().length());
        String emailTest = "/^\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]*\\.)+[A-Za-z]{2,14}$/";
        String phoneTest = "/^((13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7})$/";

        if (user.getUsername().equals("") || user.getEmail().equals("") || user.getPassword().equals("") || user.getPhone().equals("")) {
            result.setStatus(404);
            result.setMsg("填写信息不全");
            return result;
        }
        if (user.getEmail().matches(emailTest)==false || user.getPhone().matches(phoneTest)==false){
            result.setStatus(500);
            result.setMsg("邮箱或手机号不合法");
            return result;
        }
        User user1 = userService.getUserByName(user.getUsername());

        if (user1 != null) {
            result.setStatus(2002);
            result.setMsg("账号已存在，注册失败!");
        } else {
            String userid = UUID.randomUUID().toString();
            Map<String, String> keyMap = RSAUtils.createKeys(512);
            String publicKey = keyMap.get("publicKey");
            String privateKey = keyMap.get("privateKey");

            //公钥加密
            String encodedData = RSAUtils.publicEncrypt(user.getPassword(), RSAUtils.getPublicKey(publicKey));
            String date = DateUtil.ptfDate();
//            userService.register(userid,,eusernamemail,encodedData,phone,"可用",date,privateKey);
            result.setStatus(200);
            result.setMsg("注册成功!");
        }
        return result;
    }

    @RequestMapping("/login")
    public LoginResult Login(
            @RequestBody User user1)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        String username = user1.getUsername();
        String password = user1.getPassword();
//        System.out.println(username);
//        System.out.println(password);
        User user = userService.getUserByName(username);
        LoginResult result = new LoginResult();
        LoginData loginData = new LoginData();
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
            } else {
                result.setStatus(2007);
                result.setMsg("密码错误");
            }
        }
        return result;
    }

}
