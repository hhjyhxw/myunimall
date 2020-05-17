package com.icloud.api.newmall.address;

import com.alibaba.fastjson.JSON;
import com.icloud.annotation.LoginUser;
import com.icloud.api.newmall.address.service.MallUserAddressService;
import com.icloud.basecommon.model.MapRespone;
import com.icloud.common.validator.ValidatorUtils;
import com.icloud.exceptions.MallServiceException;
import com.icloud.modules.unimall.dto.UnimallAddressDTO;
import com.icloud.modules.unimall.entity.UnimallAddress;
import com.icloud.modules.unimall.entity.UnimallUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/mall/address")
public class MallUserAddressController {

    private final Logger logger = LoggerFactory.getLogger(MallUserAddressController.class);
    @Autowired
   private MallUserAddressService mallUserAddressService;
    /**
     * 我的收货地址
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public MapRespone list(@LoginUser UnimallUser user){
        List<UnimallAddress> list =mallUserAddressService.list(user.getId());
        logger.info("address/list:list=="+ JSON.toJSONString(list));
        return MapRespone.ok().put("list",list);
    }

    /*
      * 添加或者修改地址
     * @return
   */
    @RequestMapping("/saveorUpdate")
    @ResponseBody
    public MapRespone saveorUpdate(@RequestBody UnimallAddressDTO arrdessDTO, @LoginUser UnimallUser user){
        ValidatorUtils.validateDTO(arrdessDTO);
        UnimallAddress address = arrdessDTO.getUnimallAddress();
        address.setUserId(user.getId());
        boolean result = mallUserAddressService.saveorUpdate(address);
        return result==true?MapRespone.ok().put("receiver",address):MapRespone.error("删除失败");
    }

    /*
     * 获取地址
     * @return
     */
    @RequestMapping("/getDetail")
    @ResponseBody
    public MapRespone getDetail(Long id, @LoginUser UnimallUser user){
        if(id==null){
            throw new MallServiceException("id为空");
        }
         UnimallAddress address = mallUserAddressService.getDetail(id,user.getId());
        return MapRespone.ok().put("receiver",address);
    }
    /*
     * 删除地址
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public MapRespone delete(Long id, @LoginUser UnimallUser user){
        boolean result = mallUserAddressService.delete(id, user.getId());
        return result==true?MapRespone.ok():MapRespone.error("删除失败");
    }

    /*
     * 设置默认地址
     * @return
     */
    @RequestMapping("/setDefaut")
    @ResponseBody
    public MapRespone setDefaut(Long id, @LoginUser UnimallUser user){
        boolean result = mallUserAddressService.setDefaut(id,user.getId());
        return result==true?MapRespone.ok():MapRespone.error("设置失败");
    }
}
