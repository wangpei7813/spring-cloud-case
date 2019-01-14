package com.wang.idservice.api;

import com.wang.idservice.service.IdGeneratorService;
import com.wang.idservice.uitl.GuavaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wp
 * @date 2019/1/14 14:12
 */
@RestController
public class IdGeneratorController {

    @Resource
    private IdGeneratorService idGeneratorService;

    @GetMapping("/get-id")
    public Long getId(@RequestParam("key") String key) throws Exception {
        return Long.valueOf(String.valueOf(GuavaUtil.getId(key)));
    }

    @GetMapping("/create-id")
    public Long createId() throws Exception {
        return idGeneratorService.createId();
    }

}
