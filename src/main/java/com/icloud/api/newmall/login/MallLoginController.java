package com.icloud.api.newmall.login;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Strings;
import com.icloud.annotation.AuthIgnore;
import com.icloud.basecommon.model.MapRespone;
import com.icloud.basecommon.service.redis.RedisService;
import com.icloud.common.MD5Utils;
import com.icloud.common.util.RandomUtil;
import com.icloud.modules.unimall.entity.UnimallUser;
import com.icloud.modules.unimall.service.UnimallUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/api/mall/appLogin")
public class MallLoginController {

    private final Logger logger = LoggerFactory.getLogger(MallLoginController.class);
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisService redisService;

    @Autowired
    private UnimallUserService unimallUserService;
    /**
     * 注册
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    @AuthIgnore
    public MapRespone register(String userName, String password, String email){
        logger.info("userName=="+userName+"; password=="+password+"; email=="+email);
        if(Strings.isNullOrEmpty(userName)){
            return MapRespone.error("用户名不能为空");
        }
        if(Strings.isNullOrEmpty(password)){
            return MapRespone.error("密码不能为空");
        }
        if(Strings.isNullOrEmpty(email)){
            return MapRespone.error("邮箱不能为空");
        }
//        XSSRequestWrapper xssObject = new XSSRequestWrapper(getRequest());
//       if(!userName.equals(xssObject.getParameter("userName")) || !password.equals(xssObject.getParameter("assword"))){
//           result.put("code","0001");
//           result.put("message","账号或者密码格式不对");
//           return ajaxObjecValueJson(result);
//       }
        Object member = unimallUserService.getOne(new QueryWrapper<UnimallUser>().eq("loginname",userName));
        if(member!=null){
            return MapRespone.error("用户已存在");
        }
        member = unimallUserService.getOne(new QueryWrapper<UnimallUser>().eq("email",email));
        if(member!=null){
            return MapRespone.error("邮箱已被占用");
        }
        UnimallUser newmember = new UnimallUser();
        newmember.setPassword(MD5Utils.encode2hex(password));
        newmember.setLoginname(userName);
        newmember.setEmail(email);
        newmember.setLoginType(3);//登陆类型
        newmember.setLevel(0);//普通会员
        newmember.setGmtCreate(new Date());
        newmember.setStatus(0);
        newmember.setLastLoginIp(request.getRemoteHost() + ":"+ request.getRemotePort());
        unimallUserService.save(newmember);
        return MapRespone.ok("注册成功");
    }

    /**
     * 登陆
     * member:userName
     * member:password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    @AuthIgnore
    public MapRespone login(String userName,String password){
        logger.info("userName=="+userName+"; password=="+password);
        if(Strings.isNullOrEmpty(userName)){
            return MapRespone.error("用户名不能为空");
        }
        if(Strings.isNullOrEmpty(password)){
            return MapRespone.error("密码不能为空");
        }

        //脚本过滤
//        XSSRequestWrapper xssObject = new XSSRequestWrapper(getRequest());
//        if(!userName.equals(xssObject.getParameter("userName")) || !password.equals(xssObject.getParameter("assword"))){
//            result.put("code","0001");
//            result.put("message","账号或者密码格式不对");
//            return ajaxObjecValueJson(result);
//        }
        Object member = unimallUserService.getOne(new QueryWrapper<UnimallUser>().eq("loginname",userName));
        if(member==null){
            return MapRespone.error("用户不存在");
        }
        UnimallUser user = (UnimallUser)member;
        if(!MD5Utils.encode2hex(password).equals(user.getPassword())){
            return MapRespone.error("密码不正确");
        }
        String accessToken = RandomUtil.getRandomString(20);
        redisService.set(accessToken,user,3000L);//3000秒 50分
        return MapRespone.ok("登陆成功");

    }


}
