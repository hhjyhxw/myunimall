package com.icloud.api.newmall.address.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.icloud.modules.unimall.entity.UnimallAddress;
import com.icloud.modules.unimall.service.UnimallAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MallUserAddressService {

    @Autowired
    private UnimallAddressService unimallAddressService;

    public List<UnimallAddress> list(Long userId){
        return unimallAddressService.list(new QueryWrapper<UnimallAddress>()
                .eq("user_id", userId));
    }

    public boolean saveorUpdate(UnimallAddress address) {
        if(address.getId()!=null){
            if(address.getDefaultAddress()==1){
                UnimallAddress preDefault = new UnimallAddress();
                preDefault.setDefaultAddress(0);
                //把原来的默认地址更新为非默认地址
                boolean result = unimallAddressService.update(preDefault  , new UpdateWrapper<UnimallAddress>()
                        .eq("user_id", address.getUserId())
                        .eq("default_address", 1));
            }
            address.setGmtUpdate(new Date());
            return  unimallAddressService.updateById(address);
        }else{
            address.setGmtCreate(new Date());
           return unimallAddressService.save(address);
        }
    }


    public UnimallAddress getDetail(Long id,Long userId) {
        return (UnimallAddress) unimallAddressService.getOne(new QueryWrapper<UnimallAddress>()
                .eq("id", id).eq("user_id", userId));
    }

    public boolean delete(Long id, Long userId) {
        UnimallAddress entity = (UnimallAddress) unimallAddressService.getOne(new QueryWrapper<UnimallAddress>()
                .eq("id", id).eq("user_id", userId));
        return unimallAddressService.removeById(entity);
    }


    public boolean setDefaut(Long id, Long userId) {
        UnimallAddress preDefault = new UnimallAddress();
        preDefault.setDefaultAddress(0);
        //把原来的默认地址更新为非默认地址
        boolean result = unimallAddressService.update(preDefault  , new UpdateWrapper<UnimallAddress>()
                .eq("user_id", userId)
                .eq("default_address", 1));

        //设置默认地址
        preDefault.setDefaultAddress(1);
      return unimallAddressService.update(preDefault  , new UpdateWrapper<UnimallAddress>()
                .eq("id", id)
                .eq("user_id", userId));
    }
}
