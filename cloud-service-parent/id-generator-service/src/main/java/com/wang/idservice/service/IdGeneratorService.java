package com.wang.idservice.service;

import com.wang.idservice.uitl.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author wp
 * @date 2019/1/14 11:42
 */
@Service
@Slf4j
public class IdGeneratorService {

    /** 机器ID向左移12位 */
    private static final long workerIdShift = 11L;

    /** 数据标识id向左移17位(12+5) */
    private static final long datacenterIdShift = 17L;

    /** 时间截向左移22位(5+5+12) */
    private static final long timestampLeftShift = 22L;

    private AtomicLong atomicLong = new AtomicLong();

    /**
     * @Description: 生成ID
     * @author: wp
     * @date: 2019/1/14 14:36
     */
    public Long createId() {
        // mac地址hashCode值
        int macHashCode = getMacAddress().replace("-", "").hashCode();
        macHashCode = macHashCode < 0 ? -macHashCode : macHashCode;

        return (System.currentTimeMillis() << timestampLeftShift)
                | (Constant.ORDER_ID_GENERATOR_KEY.hashCode() << datacenterIdShift)
                | (macHashCode << workerIdShift) | atomicLong.addAndGet(1);
    }

    /**
     * @Description: 获取mac地址
     * @author: wp
     * @date: 2019/1/14 13:42
     */
    private static String getMacAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            byte[] mac;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (!netInterface.isLoopback() && !netInterface.isVirtual() && !netInterface.isPointToPoint() && netInterface.isUp()) {
                    mac = netInterface.getHardwareAddress();
                    if (mac != null) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                        }
                        if (sb.length() > 0) {
                            return sb.toString();
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("MAC地址获取失败", e);
        }
        return "";
    }
}
