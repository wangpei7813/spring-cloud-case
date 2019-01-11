package com.wang.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wang.zuulservice.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author wp
 * @date 2019/1/10 10:39
 */
@Component
@Slf4j
public class AuthZuulFilter extends ZuulFilter {

    @Value("${zuul.authFilter.ignoreUrl}")
    private String[] ignoreUrl;

    @Resource
    private RedisUtil redisUtil;


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //1 RequestContext
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //2 判断是否需要进行验证
        boolean needCheck = needCheck(request);
        log.info("是否拦截：needCheck = {}", needCheck);
        //3 获取请求携带的token
        String token = request.getHeader("x-auth-token");
        //4 进行验证
        if(StringUtils.isBlank(token) && needCheck) {
            setResponseErrorInfo(ctx, 401 , " access token is empty! ");
            return null;
        }

        if(StringUtils.isNotBlank(token) && needCheck) {
            String userId = request.getParameter("userId");
            //key存储规则: x-token  + ":"  + userId
            String cacheKey = "x-token" + ":" + userId;

            String cacheToken = (String) redisUtil.get(cacheKey);
            if(StringUtils.isNotBlank(cacheToken)) {
                //取出来缓存token后,直接进行对比
                if(token.equals(cacheToken)) {
                    redisUtil.expire(cacheKey,300);
                    ctx.addZuulRequestHeader("token", "access token");
                } else {
                    setResponseErrorInfo(ctx, 402 , " access token is error! ");
                    return null;
                }
            } else {
                setResponseErrorInfo(ctx, 403 , " access token cacheValue is empty! ");
                return null;
            }
        }
        return ctx;
    }

    private boolean needCheck(HttpServletRequest request){
        boolean needCheck;
        String uri = request.getRequestURI();
        log.info("请求URI = {}", uri);

        needCheck = Arrays.stream(ignoreUrl).noneMatch(igUri -> igUri.contains(uri));
        return needCheck;
    }

    private void setResponseErrorInfo(RequestContext ctx, int respStatusCode, String responseBody){
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(respStatusCode);
        ctx.setResponseBody(responseBody);
    }
}
