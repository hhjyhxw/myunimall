package com.icloud.config.interceptor;

import com.icloud.annotation.AuthIgnore;
import com.icloud.basecommon.service.redis.RedisService;
import com.icloud.basecommon.util.lang.StringUtils;
import com.icloud.config.global.Constants;
import com.icloud.modules.unimall.entity.UnimallUser;
import com.icloud.modules.unimall.service.UnimallUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class XcxLoginInterceptor extends HandlerInterceptorAdapter {
    private Logger log = LoggerFactory.getLogger(getClass());
    // @Autowired
    //private TbTokenService tokenService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UnimallUserService unimallUserService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        AuthIgnore annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class);
        }else{
            return true;
        }
        //如果有@IgnoreAuth注解，则不验证token
        if(annotation != null){
            return true;
        }
        //从header中获取token
        //1、
        String accessToken = request.getHeader("accessToken");
        log.info("======accessToken:{}",accessToken);
        //token为空
        if (StringUtils.isBlank(accessToken)) {
            log.info("======accessToken为空，访问失败");
            // throw new RRException(token不能为空);
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("{\"errCode\":\"1000\",\"msg\":\"accessToken为空\"}");
            out.flush();
            out.close();
            return false;
        }

        //2、
        log.info("redisService===="+redisService);
        Object unimallUser = redisService.get(accessToken);
        if (unimallUser==null) {
            log.info("======unimallUser不存在或者已经失效");
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("{\"code\":\"1000\",\"message\":\"用户没有登录,请先登录！\"}");
            out.flush();
            out.close();
            return false;
        }else{
            //用于其他方法获取用户信息
            request.setAttribute(Constants.USER_KEY, (UnimallUser)unimallUser);
//            redisService.set(unionid.toString(), t, LoginUtils.LOGIN_EXPIRY_TIME);  //重新激活登录时间
        }
        log.info("======验证token成功");
        return true;
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
