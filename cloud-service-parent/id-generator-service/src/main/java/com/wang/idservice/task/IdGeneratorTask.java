package com.wang.idservice.task;

import com.wang.idservice.service.IdGeneratorService;
import com.wang.idservice.uitl.Constant;
import com.wang.idservice.uitl.GuavaUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author wp
 * @date 2019/1/14 11:41
 */
@Component
public class IdGeneratorTask {

    @Resource
    private IdGeneratorService idGeneratorService;

    /**
     * @Description: 每天0点生成一批ID 存入缓存
     * @author: wp
     * @date: 2019/1/14 13:44
     */
	@Scheduled( cron = "0 0 0 * * ?")
    public void orderIdJob(){

        LinkedBlockingDeque<Long> orderIdQueue = GuavaUtil.orderIdQueue;

        for (int i = 0; i < Constant.ORDER_ID_MAX_SIZE; i++) {
            orderIdQueue.offer(idGeneratorService.createId());
        }

        GuavaUtil.setCache(Constant.ORDER_ID_GENERATOR_KEY, orderIdQueue);
    }

    /**
     * @Description: 每秒执行一次
     * @author: wp
     * @date: 2019/1/14 14:11
     */
    @Scheduled(cron = "0/1 * * * * *")
    public void orderIdCriticalJob() {

        LinkedBlockingDeque<Long> idQueue = GuavaUtil.getIdQueue(Constant.ORDER_ID_GENERATOR_KEY);

        int size = idQueue.size();
        // 达到临界值时
        /*if (size < Constant.ORDER_CRITICAL_VALUE) {
            for (int i = 0; i < (Constant.ORDER_ID_MAX_SIZE - size); i++) {
                idQueue.offer(idGeneratorService.createId());
            }
        }*/

        if (size < 50) {
            for (int i = 0; i < (100 - size); i++) {
                idQueue.offer(idGeneratorService.createId());
            }
        }
        GuavaUtil.setCache(Constant.ORDER_ID_GENERATOR_KEY, idQueue);
    }

}
