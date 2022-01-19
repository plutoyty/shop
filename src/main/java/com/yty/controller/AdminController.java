package com.yty.controller;


import com.alipay.easysdk.factory.Factory;
import com.yty.Vo.BaseResult;
import com.yty.Vo.LoginResult;
import com.yty.Vo.UserListResult;
import com.yty.entity.LoginData;
import com.yty.entity.User;
import com.yty.service.UserService;
import com.yty.utils.RSAUtils;
import com.yty.utils.TokenUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @SneakyThrows
    @RequestMapping("/login")
    public LoginResult Login(
            @RequestBody User user1) {
        System.out.println(user1);
        LoginResult result = new LoginResult();
        LoginData loginData = new LoginData();
        String username = user1.getUsername();
        String password = user1.getPassword();
        User user = userService.getUserByName(username);
        boolean f = userService.adminBool(String.valueOf(user.getId()));
        if (f == false) {
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

    /**
     * 获取用户
     * @return
     */
    @RequestMapping("getUsers")
    private UserListResult getUsers(){
        UserListResult userListResult = new UserListResult();
        userListResult.setStatus(100);
        userListResult.setData(userService.getUsers());
        userListResult.setCount(userService.getUsersCount());
        return userListResult;
    }

    /**
     * 删除用户
     * @param email
     * @return
     */
    @RequestMapping("deleteUser")
    private BaseResult deleteUser(@RequestParam("email")String email){
        BaseResult baseResult = new BaseResult();
        boolean f = userService.deleteUser(email);
        if(f==true){
            baseResult.setStatus(100);
            baseResult.setMsg("success");
        }else {
            baseResult.setStatus(200);
            baseResult.setMsg("failed");
        }
        return baseResult;
    }

    /**
     *
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping("updateUser")
    private BaseResult updateUser(@RequestBody User user){
        BaseResult baseResult = new BaseResult();
        boolean f =  userService.updataUser(user);
        if(f==true){
            baseResult.setStatus(100);
            baseResult.setMsg("success");
        }else {
            baseResult.setStatus(200);
            baseResult.setMsg("failed");
        }
        return baseResult;
    }

    /**
     * 查找用户
     * @param name
     * @param tel
     * @return
     */
    @RequestMapping("queryUsers")
    private UserListResult searchUsers(@RequestParam("name")String name,
                                       @RequestParam("tel")String tel){
        UserListResult userListResult = new UserListResult();
        userListResult.setData(userService.getSearchUsers(name,tel));
        userListResult.setStatus(100);
        userListResult.setCount(userListResult.getData().size());
        return userListResult;
    }
}
