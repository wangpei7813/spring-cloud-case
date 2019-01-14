package com.wang.idservice.uitl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author wp
 * @date 2019/1/14 11:28
 */
@Slf4j
public class GuavaUtil {

    // 订单ID队列
    public static LinkedBlockingDeque<Long> orderIdQueue = new LinkedBlockingDeque<>(Constant.ORDER_ID_MAX_SIZE);

    public static LinkedBlockingDeque<Long> userIdQueue = new LinkedBlockingDeque<>(Constant.USER_ID_MAX_SIZE);

    private static LoadingCache<String, Object> build = CacheBuilder
            .newBuilder()
//            .concurrencyLevel(5)
            .maximumSize(100)
            /*.removalListener(
                    new RemovalListener<String, Object>() {
                        @Override
                        public void onRemoval(RemovalNotification<String, Object> removeObj) {
                            System.out.println("------------" + removeObj.toString());
                            build.invalidate(removeObj.getKey());
                        }
                    }
            )*/
            .build(
                    new CacheLoader<String, Object>() {
                        @Override
                        public Object load(String s) throws Exception {
                            return null;
                        }
                    }
            );

    /**
     * @Description: 获取缓存ID队列
     * @author: wp
     * @date: 2019/1/12 17:06
     */
    public static LinkedBlockingDeque<Long> getIdQueue(String key) {
        @Nullable Object value = build.getIfPresent(key);

        if (value != null) {
            return (LinkedBlockingDeque<Long>) value;
        }
        return new LinkedBlockingDeque<>();
    }

    /**
     * @Description: 获取缓存
     * @author: wp
     * @date: 2019/1/12 15:20
     */
    public static Object getId(String key) {
        try {
            LinkedBlockingDeque<Long> idQueue = getIdQueue(key);
            if (idQueue != null) {
                return idQueue.poll();
            }
            return null;
        } catch (Exception e) {
            log.error("缓存不存在: ", e);
            return null;
        }
    }

    /**
     * @Description: 获取所有缓存
     * @author: wp
     * @date: 2019/1/12 15:30
     */
    public static ConcurrentMap<String, Object> getAllCache() {
        return build.asMap();
    }

    /**
     * @Description: 存入缓存
     * @author: wp
     * @date: 2019/1/12 15:22
     */
    public static void setCache(String key, Object value) {
        try {
            build.put(key, value);
        } catch (Exception e) {
            log.error("存入缓存失败");
        }
    }
}
