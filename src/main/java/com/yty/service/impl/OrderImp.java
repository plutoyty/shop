package com.yty.service.impl;

import com.yty.dao.OrderMapper;
import com.yty.entity.Address;
import com.yty.entity.CartItem;
import com.yty.entity.Order;
import com.yty.service.OrderService;
import com.yty.utils.DateUtil;
import com.yty.utils.OrderNumUtil;
import com.yty.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderImp implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Order creatOrder(String email, List<CartItem> list, Address address) {
        Integer sum = 0;
        for(CartItem c:list){
            sum+=c.getProduct().getPrice()*c.getCount();
        }
        Order order = new Order();
        order.setCreatDate(DateUtil.ptfDate());
        order.setUpdateDate(DateUtil.ptfDate());
        order.setOrderId(String.valueOf(OrderNumUtil.getOrderIdByUUId()));
        order.setName(address.getName());
        order.setCity(address.getCity());
        order.setProvince(address.getProvince());
        order.setRegion(address.getRegion());
        order.setStatus("未支付");
        order.setSum(sum);
        order.setTel(address.getTel());
        order.setGoods(list);
        order.setDetail(address.getDetail());
        orderMapper.creatOrder(order,email);
        redisUtil.set("order_"+order.getOrderId(),order.getOrderId(),1800);
        for(CartItem c:list){
            orderMapper.addGoods(c.getProduct().getId(),order.getOrderId(),String.valueOf(c.getCount()));
        }
        return order;
    }

    @Override
    public List<Order> getAllByEmail(String email) {
        return orderMapper.getAllByEmail(email);
    }

    @Override
    public Order getOrderById(String email, String orderId) {
        return orderMapper.getOrderById(email,orderId);
    }

    @Override
    public boolean delete( String orderId) {
        return orderMapper.deleteOrder(orderId);
    }

    @Override
    public Order getOrderById(String orderId) {
        if (orderId==null || orderId.equals(""))
        return null;
        else {
            return orderMapper.getOrder(orderId);
        }
    }

    @Override
    public List<CartItem> getGoodsByOrderId(String orderId) {
        return orderMapper.getOrderGoods(orderId);
    }

    @Override
    public boolean updateStatus(String status, String orderId) {
        String date = DateUtil.ptfDate();
        return orderMapper.updateStatus(status,orderId,date);
    }

    @Override
    public List<Order> selectOrder(String orderId, String status, String page, String pageSize) {
        Integer start = Integer.valueOf(page) * Integer.valueOf(pageSize)-Integer.valueOf(pageSize);
        Integer end = start+Integer.valueOf(pageSize)+1;
        return orderMapper.selectOrder(orderId,status,start,end);
    }
}
