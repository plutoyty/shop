package com.yty.controller;

import com.yty.Vo.BaseResult;
import com.yty.Vo.UserinfoResult;
import com.yty.entity.User;
import com.yty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("personal")
public class PersonalController {

    @Autowired
    private UserService userService;

    @RequestMapping("test")
    private String test(){
        return "test";
    }

    /**
     * 获取用户信息
     * @param email
     * @return
     */
    @RequestMapping("/userInfo")
    private UserinfoResult getUserInfo(@RequestParam("email") String email){
        UserinfoResult userinfoResult = new UserinfoResult();
        User user = userService.getUserByName(email);
        if (user != null){
            userinfoResult.setStatus(100);
            userinfoResult.setData(user);
            userinfoResult.getData().setPassword("");
        }else {
            userinfoResult.setStatus(200);
        }
        return userinfoResult;
    }

    /**
     * 修改信息
     * @param user
     * @return
     */
    @RequestMapping("/changeUserInfo")
    private UserinfoResult changeInfo(
             @RequestBody User user){
        System.out.println(user);
        UserinfoResult userinfoResult = new UserinfoResult();
        boolean f = userService.changeUserInfo(user);
        if (f==true){
            userinfoResult.setStatus(100);
            user.setId(0);
            userinfoResult.setData(user);
        }else {
            userinfoResult.setStatus(200);
        }
        return userinfoResult;
    }

    /**
     * 换头像
     * @param file
     * @return
     */
    @RequestMapping("/head")
    private BaseResult head(@RequestParam("file")MultipartFile file){
        BaseResult baseResult = new BaseResult();
        String s = userService.uploadAvatar(file);
        if (s==null){
            baseResult.setStatus(200);
            baseResult.setMsg("failed");
        }else {
            baseResult.setStatus(100);
            baseResult.setMsg(s);
        }
        return baseResult;
    }

}
