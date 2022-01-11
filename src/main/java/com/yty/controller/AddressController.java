package com.yty.controller;

import com.yty.Vo.AllAddressResult;
import com.yty.Vo.BaseResult;
import com.yty.entity.Address;
import com.yty.service.AddressService;
import com.yty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @RequestMapping("getAll")
    public AllAddressResult getAll(@RequestParam("email") String email){
//        System.out.println(email);
        AllAddressResult allAddressResult = new AllAddressResult();
        allAddressResult.setData(addressService.getAllAddress(email));
        if (allAddressResult.getData()!=null){
            allAddressResult.setStatus(100);
        }else {
            allAddressResult.setStatus(200);
        }
        return allAddressResult;
    }

    @RequestMapping("/default")
    public BaseResult setDefault(@RequestParam("id") String id,@RequestParam("email") String email){
        BaseResult baseResult = new BaseResult();
        addressService.cancel(email);
        boolean f = addressService.setDefault(id);
        if (f==true){
            baseResult.setStatus(100);
            baseResult.setMsg("设置成功");
        } else {
            baseResult.setStatus(200);
            baseResult.setMsg("设置失败");
        }
        return baseResult;
    }

    @RequestMapping("/addAddress")
    private BaseResult addAddress(@RequestParam("email")String email,
    @RequestBody Address address){
        BaseResult baseResult = new BaseResult();
//        addressService
        return baseResult;
    }

}
