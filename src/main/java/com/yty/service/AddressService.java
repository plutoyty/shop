package com.yty.service;


import com.yty.entity.Address;

import java.util.List;

public interface AddressService {


    /**
     * 通过邮箱获取所有的地址
     * @param email
     * @return
     */
    List<Address> getAllAddress(String email);

    /**
     * 设置默认地址
     * @param id
     * @return
     */
    boolean setDefault(String id);

    /**
     * 取消改email的默认地址
     * @param email
     */
    void cancel(String email);

    /**
     * 新增一条地址
     * @param address
     * @param email
     * @param id
     * @return
     */
    boolean addAddress(Address address,String email,Integer id);

    /**
     * 删除地址
     * @param email
     * @param id
     * @return
     */
    boolean delete(String email, String id);

    /**
     * 获取默认地址
     * @param email
     * @return
     */
    Address getDefault(String email);

    /**
     * 修改地址
     * @param address
     * @param email
     * @return
     */
    boolean updateAddress(Address address, String email,String id);
}
