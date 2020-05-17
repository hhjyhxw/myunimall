package com.icloud.api.unimall.address;

import com.icloud.annotation.LoginUser;
import com.icloud.api.unimall.address.service.AddressService;
import com.icloud.basecommon.model.ResultResponse;
import com.icloud.exceptions.unimall.ServiceException;
import com.icloud.modules.unimall.entity.UnimallUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
@author kbq
@date  2019/7/4 - 22:07
*/
@Controller
@RequestMapping("/api/unimall/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 添加用户地址
     * @param province 省份
     * @param city 城市
     * @param county 区县
     * @param address 详细地址
     * @param defaultAddress 默认地址
     * @param userId 用户ID
     * @param phone 电话
     * @param consignee 收件人
     * @return
     * @throws ServiceException
     */
    //option的value的内容是这个method的描述，notes是详细描述，response是最终返回的json model。其他可以忽略
//    @ApiOperation(value = "绑定烟时扫二维码获取信息", notes = "增加收货地址")
    @RequestMapping("/addAddress")
    @ResponseBody
    public ResultResponse addAddress(String province, String city, String county, String address, Integer defaultAddress, Long userId, String phone, String consignee, @LoginUser UnimallUser user) throws ServiceException {
        Boolean result = addressService.addAddress(province,city,county,address,defaultAddress,user.getId(),phone,consignee);
       return new ResultResponse().okOrError(result);
    }

    @RequestMapping("/deleteAddress")
    @ResponseBody
    public ResultResponse deleteAddress(Long addressId, Long userId,@LoginUser UnimallUser user) throws ServiceException {
        Boolean result =  addressService.deleteAddress(addressId,user.getId());
        return new ResultResponse().okOrError(result);
    }

    @RequestMapping("/updateAddress")
    @ResponseBody
    public ResultResponse updateAddress(Long addressId, String province, String city, String county, String address, Integer defaultAddress, Long userId, String phone, String consignee) throws ServiceException {
        Boolean result = addressService.updateAddress(addressId,province,city,county,address,defaultAddress,userId,phone,consignee);
        return new ResultResponse().okOrError(result);
    }

    @RequestMapping("/getAllAddress")
    @ResponseBody
    public ResultResponse getAllAddress(Long userId,@LoginUser UnimallUser user) throws ServiceException {
        return new ResultResponse().ok(addressService.getAllAddress(userId));
    }

    @RequestMapping("/getAddressById")
    @ResponseBody
    public ResultResponse getAddressById(Long addressId) throws ServiceException {
        return new ResultResponse().ok(addressService.getAddressById(addressId));
    }

    @RequestMapping("/getDefAddress")
    @ResponseBody
    public ResultResponse getDefAddress(Long userId,@LoginUser UnimallUser user) throws ServiceException {
        return new ResultResponse().ok(addressService.getDefAddress(user.getId()));
    }

}
