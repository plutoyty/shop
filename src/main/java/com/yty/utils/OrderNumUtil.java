package com.yty.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OrderNumUtil {

    public static final int MACHINE_ID = 1;

    /**
     * 根据UUID生成订单号
     *
     * @return
     */
    public static String getOrderIdByUUId() {
        int value = UUID.randomUUID().toString().hashCode();
        if (value < 0) {
            value = -value;
        }
        //0代表前面补充0，10代表长度，d代表正整数
        String orderId = String.format("%010d", value);
        return orderId;
    }

    /**
     * 锁对象，可以为任意对象
     */
    private static Object lockObj = "lockerOrder";
    /**
     * 订单号生成计数器
     */
    public static long orderNumCount = 0L;
    /**
     * 每毫秒生成订单号数量最大值
     */
    public static int maxPerMSECSize = 1000;

    /**
     * 生成非重复订单号，理论上限1毫秒1000个，可扩展
     */
    public static String makeOrderNum() {
        String finOrderNum = "";
        try {
            // 最终生成的订单号
            synchronized (lockObj) {
                // 取系统当前时间作为订单号变量前半部分，精确到毫秒
                long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
                // 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
                if (orderNumCount > maxPerMSECSize) {
                    orderNumCount = 0L;
                }
                //组装订单号
                String countStr = maxPerMSECSize + orderNumCount + "";
                finOrderNum = nowLong + countStr.substring(1);
                orderNumCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finOrderNum;
    }

    public static void main(String[] args) {
        System.out.println(OrderNumUtil.getOrderIdByUUId());
        System.out.println(OrderNumUtil.makeOrderNum());
    }
}
