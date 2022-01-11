package com.yty.service.impl;

import com.yty.dao.AddressMapper;
import com.yty.entity.Address;
import com.yty.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressImp implements AddressService {

    @Autowired
    public AddressMapper addressMapper;

    @Override
    public List<Address> getAllAddress(String email) {
        return addressMapper.getAllAddress(email);
    }

    @Override
    public boolean setDefault(String id) {
        return addressMapper.setDefault(id);
    }

    @Override
    public void cancel(String email) {
        addressMapper.cancelDefault(email);
    }

    @Override
    public boolean addAddress(Address address, String email, String id) {
        return addressMapper.addAddress(address,email,id);
    }


}
