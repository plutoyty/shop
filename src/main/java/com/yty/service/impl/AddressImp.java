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
    public Address getAddress(String id) {
        return addressMapper.getAddressById(id);
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
    public boolean addAddress(Address address, String email, Integer id) {
        return addressMapper.addAddress(address,email,id);
    }

    @Override
    public boolean delete(String email, String id) {
        return addressMapper.deleteAddress(email,id);
    }

    @Override
    public Address getDefault(String email) {
        return addressMapper.getAddress(email);
    }

    @Override
    public boolean updateAddress(Address address, String email,String id) {
        return addressMapper.update(address,email,id);
    }
}
