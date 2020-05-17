package com.icloud.api.unimall.address.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.icloud.exceptions.unimall.AppServiceException;
import com.icloud.exceptions.unimall.ExceptionDefinition;
import com.icloud.exceptions.unimall.ServiceException;
import com.icloud.modules.unimall.entity.UnimallAddress;
import com.icloud.modules.unimall.service.UnimallAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AddressService {

    @Autowired
    private UnimallAddressService unimallAddressService;

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
    public Boolean addAddress(String province, String city, String county, String address, Integer defaultAddress, Long userId, String phone, String consignee) throws ServiceException {
        //查询用户所有地址
        Integer addressNum = unimallAddressService.count(new QueryWrapper<UnimallAddress>().eq("user_id", userId));
        UnimallAddress addressDO = null;
        if (addressNum == 0) {
            addressDO = new UnimallAddress(province, city, county, address, 1, userId, phone, consignee);
        } else {
            //defaultAddress = 1设置新添加地址为默认地址
            if (defaultAddress != 0) {
                UnimallAddress preDefault = new UnimallAddress();
                preDefault.setDefaultAddress(0);
                //传入entity以及更新条件进行更新信息
                //跟新用户id = userId,default_address 默认地址=1 的记录为 0
                boolean result = unimallAddressService.update(preDefault  , new UpdateWrapper<UnimallAddress>()
                        .eq("user_id", userId)
                        .eq("default_address", 1));

                //该用户有地址却没有默认地址，抛出该异常
                if (!result) {
                    throw new AppServiceException(ExceptionDefinition.ADDRESS_QUERY_FAILED);
                }
                //添加默认地址
                addressDO = new UnimallAddress(province, city, county, address, 1, userId, phone, consignee);
            } else {
                //添加一般地址
                addressDO = new UnimallAddress(province, city, county, address, 0, userId, phone, consignee);
            }
        }
        Date now = new Date();
        addressDO.setGmtCreate(now);
        addressDO.setGmtUpdate(now);
        if (unimallAddressService.save(addressDO)) {
            return true;
        } else {
            throw new AppServiceException(ExceptionDefinition.ADDRESS_DATABASE_QUERY_FAILED);
        }
    }

    /**
     * 删除收货地址
     * @param addressId 地址id
     * @param userId    用户id
     * @return
     * @throws ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteAddress(Long addressId, Long userId) throws ServiceException {
        //查询判断是否存在默认地址
        Integer defaultNum = unimallAddressService.count(new QueryWrapper<UnimallAddress>()
                .eq("user_id", userId)
                .eq("id", addressId)
                .eq("default_address", 1));
        //非默认地址可以删除
        if (defaultNum == 0) {
            return unimallAddressService.remove(new QueryWrapper<UnimallAddress>()
                    .eq("id", addressId)
                    .eq("user_id", userId));
        } else {

            if (!(unimallAddressService.remove(new QueryWrapper<UnimallAddress>()
                    .eq("id", addressId)
                    .eq("user_id", userId)))) {
                throw new AppServiceException(ExceptionDefinition.ADDRESS_DATABASE_QUERY_FAILED);
            } else {
                List<UnimallAddress> addressDOS = unimallAddressService.list(new QueryWrapper<UnimallAddress>().eq("user_id", userId));
                if (addressDOS.size() != 0) {
                    UnimallAddress addressDO = addressDOS.get(0);
                    addressDO.setDefaultAddress(1);
                    return unimallAddressService.updateById(addressDO);
                }
                return true;
            }
        }
    }


    /**
     * 修改收货地址
     * @param addressId 地址Id
     * @param province  省份
     * @param city      城市
     * @param county    城市
     * @param address  详细地址
     * @param defaultAddress    默认地址
     * @param userId            用户ID
     * @param phone 电话
     * @param consignee 收件人
     * @return
     * @throws ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateAddress(Long addressId, String province, String city, String county, String address, Integer defaultAddress, Long userId, String phone, String consignee) throws ServiceException {
        UnimallAddress addressDO = new UnimallAddress(province, city, county, address, defaultAddress, userId, phone, consignee);
        Date now = new Date();
        if (defaultAddress != 0) {//设置为默认地址
            defaultAddress = 1;
            //修改默认地址为非默认地址
            List<UnimallAddress> addressDOS = unimallAddressService.list(new QueryWrapper<UnimallAddress>().eq("user_id", userId).eq("default_address", 1));
            if (addressDOS.size() != 0) {
                UnimallAddress preDefault = addressDOS.get(0);
                preDefault.setDefaultAddress(0);
                unimallAddressService.updateById(preDefault);
            }
        }
        addressDO.setDefaultAddress(defaultAddress);
        addressDO.setGmtUpdate(now);
        return unimallAddressService.update(addressDO, new UpdateWrapper<UnimallAddress>()
                .eq("id", addressId)
                .eq("user_id", userId));

    }

    /**
     * 查询用户所有收货地址
     * @param userId
     * @return
     * @throws ServiceException
     */
    public List<UnimallAddress> getAllAddress(Long userId) throws ServiceException {
        return unimallAddressService.list(new QueryWrapper<UnimallAddress>()
                .eq("user_id", userId));
    }

    /**
     * 根据地址ID，查询收货地址
     * @param addressId
     * @return
     * @throws ServiceException
     */
    public UnimallAddress getAddressById(Long addressId) throws ServiceException {
        return (UnimallAddress) unimallAddressService.getById(addressId);
    }

    /**
     * 获取用户默认地址
     * @param userId
     * @return
     * @throws ServiceException
     */
    public UnimallAddress getDefAddress(Long userId) throws ServiceException {
        List<UnimallAddress> addressDOS = unimallAddressService.list(
                new QueryWrapper<UnimallAddress>()
                        .eq("user_id", userId)
                        .eq("default_address", 1));
        if (CollectionUtils.isEmpty(addressDOS)) {
            UnimallAddress addressDO = new UnimallAddress();
            addressDO.setCity("XXX");
            addressDO.setProvince("XXX");
            addressDO.setCounty("XXX");
            addressDO.setAddress("不需要收货地址");
            addressDO.setConsignee("匿名");
            addressDO.setPhone("XXXXXXXXXXX");
            return addressDO;
        } else {
            return addressDOS.get(0);
        }
    }


}
