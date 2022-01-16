package com.yty.controller;

import com.yty.Vo.AddressResult;
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

    /**
     * 获取所有地址
     * @param email
     * @return
     */
    @RequestMapping("getAll")
    public AllAddressResult getAll(
            @RequestParam("email") String email) {
        AllAddressResult allAddressResult = new AllAddressResult();
        allAddressResult.setData(addressService.getAllAddress(email));
        if (allAddressResult.getData() != null) {
            allAddressResult.setStatus(100);
        } else {
            allAddressResult.setStatus(200);
        }
        return allAddressResult;
    }

    /**
     * 获取默认地址
     * @param email
     * @return
     */
    @RequestMapping("getDefault")
    public AddressResult getDefault(@RequestParam("email") String email) {
        AddressResult addressResult = new AddressResult();
        addressResult.setData(addressService.getDefault(email));
        addressResult.setStatus(100);
        return addressResult;
    }

    /**
     * 设置默认地址
     * @param id
     * @param email
     * @return
     */
    @RequestMapping("/default")
    public BaseResult setDefault(@RequestParam("id") String id,
                                 @RequestParam("email") String email) {
        BaseResult baseResult = new BaseResult();
        addressService.cancel(email);
        boolean f = addressService.setDefault(id);
        if (f == true) {
            baseResult.setStatus(100);
            baseResult.setMsg("设置成功");
        } else {
            baseResult.setStatus(200);
            baseResult.setMsg("设置失败");
        }
        return baseResult;
    }

    /**
     * 新增地址
     * @param email
     * @param address
     * @return
     */
    @RequestMapping("/addAddress")
    private BaseResult addAddress(@RequestParam("email") String email,
                                  @RequestBody Address address) {
        System.out.println(email+address);
        BaseResult baseResult = new BaseResult();
        Integer id = userService.getUserByName(email).getId();
        if (addressService.addAddress(address, email, id) == true) {
            baseResult.setStatus(100);
            baseResult.setMsg("添加成功");
        } else {
            baseResult.setStatus(100);
            baseResult.setMsg("添加失败");
        }
        return baseResult;
    }

    /**
     * 删除地址
     * @param email
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    private BaseResult deleteAddress(@RequestParam("email") String email,
                                     @RequestParam("id") String id) {
        System.out.println(email+id);
        BaseResult baseResult = new BaseResult();
        if (addressService.delete(email,id)==true){
            baseResult.setStatus(100);
            baseResult.setMsg("删除成功");
        }else {
            baseResult.setStatus(200);
            baseResult.setMsg("删除失败");
        }
        return baseResult;
    }


    /**
     * 修改地址
     * @param email
     * @param id
     * @param address
     * @return
     */
    @PutMapping("/update/{id}")
    private BaseResult updateAddress(@RequestParam("email") String email,@PathVariable("id")String id,
                                      @RequestBody Address address) {
        BaseResult baseResult = new BaseResult();
        if (addressService.updateAddress(address, email,id) == true) {
            baseResult.setStatus(100);
            baseResult.setMsg("修改成功");
        } else {
            baseResult.setStatus(100);
            baseResult.setMsg("修改失败");
        }
        return baseResult;
    }

}
