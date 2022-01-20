package com.yty.service.impl;

import com.yty.dao.UserMapper;
import com.yty.entity.User;
import com.yty.service.UserService;
import com.yty.utils.QiniuCloudUtil;
import com.yty.utils.RSAUtils;
import com.yty.utils.RedisUtil;
import com.yty.utils.SendEmailUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void print() {
//        userMapper.login();
        System.out.println(userMapper);
        System.out.println(redisUtil);
        redisUtil.set("123", "qqwqqwqq");
        System.out.println(redisUtil.get("123"));
        System.out.println("running------ ");
    }

    @Override
    public User login(String account, String password) {
        User user = new User();
        return null;
    }

    @Override
    public boolean register(User user) {
        return userMapper.register(user);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUser(name);
    }

    @SneakyThrows
    @Override
    public String sendEmail(String email, String msg) {
        if (redisUtil.hasKey(email + msg) == true) {
            return "" + redisUtil.getExpire(email + msg);
        }
        String code = makeCode();
        SendEmailUtil.sendMail(email, code);
        redisUtil.set(email + msg, code, 60);
        return code;
    }

    @Override
    public String makeCode() {
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        String code1 = String.valueOf(code);
        return code1;
    }

    @Override
    public Integer verifyRegisterInfo(String email, String tel, String password, String name, String code) {
        String emailTest = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String phoneTest = "^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])\\d{8}$";
        if (name.equals("") || email.equals("") || password.equals("") || tel.equals("") || code.equals("")) {
            return 404;
        }
        if (email.matches(emailTest) == false || tel.matches(phoneTest) == false) {
            return 500;
        }
        if (!redisUtil.hasKey(email + "emailCode") || redisUtil.hasKey(email + "emailCode") && !code.equals(redisUtil.get(email + "emailCode"))) {
            return 101;
        }
        return 1010;
    }

    @Override
    public boolean changeUserInfo(User user) {
        User user1 = userMapper.getUser(user.getEmail());
        String sex = user.getSex();
        if (sex.equals("男")) {
            user.setSex("1");
        } else if (sex.equals("女")) {
            user.setSex("0");
        } else {
            user.setSex("-1");
        }
        user.setId(user1.getId());
        return userMapper.changUserInfo(user);
    }

    @Override
    public boolean adminBool(String id) {
        return userMapper.admin(id).equals("1");
    }

    @Override
    public boolean CheckCode(String email, String code, String type) {

        if (!redisUtil.hasKey(email + type) || redisUtil.hasKey(email + type) && !code.equals(redisUtil.get(email + type))) {
            return false;
        }
        return true;
    }

    @Override
    public void updatePwd(String email, String newPassword,String privateKey) {
        userMapper.updatePwd(email, newPassword,privateKey);
    }

    @SneakyThrows
    @Override
    public List<User> getUsers() {
        List<User> list = userMapper.getUsers();
        for (User c : list) {
            c.setPassword(RSAUtils.privateDecrypt(c.getPassword(), RSAUtils.getPrivateKey(c.getPravitekey().trim())));
            c.setPravitekey("");
        }
        return list;
    }

    @Override
    public Integer getUsersCount() {
        return userMapper.getUsersCount();
    }

    @Override
    public boolean deleteUser(String email) {
        return userMapper.deleteUser(email);
    }

    @SneakyThrows
    @Override
    public boolean updataUser(User user) {
        Map<String, String> keyMap = RSAUtils.createKeys(512);
        String publicKey = keyMap.get("publicKey");
        String privateKey = keyMap.get("privateKey");
        /**
         * 公钥加密
         */
        String encodedData = RSAUtils.publicEncrypt(user.getPassword(), RSAUtils.getPublicKey(publicKey));
        user.setPassword(encodedData);
        user.setPravitekey(privateKey);
        return userMapper.updateUser(user);
    }

    @SneakyThrows
    @Override
    public List<User> getSearchUsers(String name, String tel) {
        List<User> list = userMapper.getSearchUsers(name, tel);
        for (User c : list) {
            c.setPassword(RSAUtils.privateDecrypt(c.getPassword(), RSAUtils.getPrivateKey(c.getPravitekey().trim())));
            c.setPravitekey("");
        }
        return list;
    }

    @SneakyThrows
    @Override
    public String uploadAvatar(MultipartFile avatar) {
        if (avatar.isEmpty()) {
            return null;
        }
        byte[] bytes = avatar.getBytes();
        String imageName = UUID.randomUUID().toString();
        QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
        //使用base64方式上传到七牛云
        String url = qiniuUtil.put64image(bytes, imageName);
        return url;  // success
    }


}
