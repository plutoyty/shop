package com.yty.dao;

import com.yty.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AddressMapper {

    /**
     * 获取改email的所有地址
     * @param email
     * @return
     */
    List<Address> getAllAddress(String email);

    /**
     * 将id设置成默认地址
     * @param id
     * @return
     */
    boolean setDefault(String id);

    /**
     * 取消改Email的默认地址
     * @param email
     */
    void cancelDefault(String email);

    /**
     * 新增地址
     * @param address
     * @param email
     * @return
     */
    boolean addAddress(@Param("address") Address address, @Param("email")String email,@Param("id")Integer id);

    /**
     * 删除地址
     * @param email
     * @param id
     * @return
     */
    boolean deleteAddress(@Param("email") String email,@Param("id")String id);

    /**
     * 获取默认地址
     * @param email
     * @return
     */
    Address getAddress(String email);

    /**
     * 修改地址
     * @param address
     * @param email
     * @return
     */
    boolean update(@Param("address") Address address,@Param("email") String email,@Param("id")String id);

    /**
     * 获取该id的地址
     * @param id
     * @return
     */
    Address getAddressById(String id);
}
