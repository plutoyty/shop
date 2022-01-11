package com.yty.controller;

import com.yty.Vo.CodeResult;
import com.yty.Vo.RegResult;
import com.yty.Vo.UserinfoResult;
import com.yty.entity.LoginData;
import com.yty.Vo.LoginResult;
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
    public RegResult register(@RequestParam("code") String code,
                              @RequestBody User user)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        RegResult result = new RegResult();
        Integer status = userService.verifyRegisterInfo(user.getEmail(), user.getPhone(), user.getPassword(), user.getUsername(), code);
        if (status == 404) {
            result.setStatus(404);
            result.setMsg("填写信息不全");
            return result;
        } else if (status == 500) {
            result.setStatus(500);
            result.setMsg("邮箱或手机号不合法");
            return result;
        } else if (status == 101) {
            result.setStatus(101);
            result.setMsg("邮箱码错误");
            return result;
        }
        User user1 = userService.getUserByName(user.getEmail());
        User user2 = userService.getUserByName(user.getPhone());
        System.out.println(user1+""+user2);
        if (user1 != null && user2!= null) {
            result.setStatus(2002);
            result.setMsg("邮箱或电话已被注册，注册失败!");
        } else {
            String userid = UUID.randomUUID().toString();
            Map<String, String> keyMap = RSAUtils.createKeys(512);
            String publicKey = keyMap.get("publicKey");
            String privateKey = keyMap.get("privateKey");
            /**
             * 公钥加密
             */
            String encodedData = RSAUtils.publicEncrypt(user.getPassword(), RSAUtils.getPublicKey(publicKey));
            String date = DateUtil.ptfDate();
            user.setPassword(encodedData);
            user.setPravitekey(privateKey);
            user.setAccount(userid);
            user.setStatus("1");
            user.setDate(date);
            boolean f = userService.register(user);
            System.out.println(f);
            if (f == true) {
                result.setStatus(200);
                result.setMsg("注册成功!");
            } else {
                result.setStatus(300);
                result.setMsg("注册失败!");
            }
        }
        return result;
    }

    @RequestMapping("/login")
    public LoginResult Login(
             @RequestBody User user1)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        String username = user1.getUsername();
        String password = user1.getPassword();
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
                result.setEmail(user.getEmail());
                result.setName(user.getUsername());
            } else {
                result.setStatus(2007);
                result.setMsg("密码错误");
            }
        }
        return result;
    }

    @RequestMapping("/sendcode")
    public CodeResult sendCode(@RequestParam("email") String email) {
        CodeResult codeResult = new CodeResult();
        try {
            String code = userService.sendEmail(email);
            System.out.println(code);
            if (Integer.valueOf(code) <= 60) {
                codeResult.setStatus(300);
                codeResult.setMsg(code);
                return codeResult;
            }
        } catch (Exception e) {
            codeResult.setStatus(200);
            return codeResult;
        }
        codeResult.setStatus(100);
        return codeResult;
    }

}
