package com.yty.listen;

import com.yty.entity.Order;
import com.yty.service.OrderService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RedisKeyExpirationListener
 * @Description: redis过期监听
 * @author: yty
 * @Date: 2022/1/23 23:06
 * @Version: 1.0
 */
@Slf4j
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Autowired
    private OrderService orderService;

    /**
     * 针对redis数据失效事件，进行数据处理
     *
     * @param message
     * @param pattern
     */
    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = message.toString();
        //从失效key中筛选代表订单失效的key
        if (key != null && key.startsWith("order_")) {
            Order order = orderService.getOrderById(key.substring(6));
            if (order.getStatus().equals("未支付")){
                orderService.updateStatus("已取消",order.getOrderId());
                log.info("订单号为【" + key + "】超时未支付-自动修改为已取消状态");
            }
        }
    }
}